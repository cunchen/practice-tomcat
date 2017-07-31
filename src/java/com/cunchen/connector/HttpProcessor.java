package com.cunchen.connector;

import com.cunchen.server.header.HttpHeader;
import com.cunchen.server.processor.ServletProcessor;
import com.cunchen.server.processor.StaticResourceProcessor;
import com.cunchen.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Http解析器
 * Created by wqd on 2016/12/28.
 */
public class HttpProcessor implements Runnable {

    private HttpConnector httpConnector ;
    private HttpRequest request;
    private HttpResponse response;


    private String uri;
    private String protocol;

    private RequestLine requestLine = new RequestLine();

    //是否有新socket接入
    private boolean available;

    //多线程ID
    private final int id ;

    //Socket
    private Socket scoket;

    public HttpProcessor(HttpConnector httpConnector, int id) {
        this.httpConnector = httpConnector;
        this.id = id;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            InputStream stream = socket.getInputStream();
            input = new SocketInputStream(stream, 2048);
            request = new HttpRequest(input);

            output = socket.getOutputStream();
            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server", "Pyrmont Servlet Container");

            parseRequest(input, output);
            parseHeaders(input);

            if(request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();

        } catch (IOException | NullPointerException | ServletException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Io流Request解析
     *
     * @param input  输入流
     * @param output 输出流
     */
    private void parseRequest(SocketInputStream input, OutputStream output) throws NullPointerException, ServletException {
        input.readRequestLine(requestLine);
        String method = new String(requestLine.method, 0, requestLine.methodEnd);

        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }

        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }

        request.setUri(uri);

        //Checking for an absolute URI
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            //Parsing out protocol and hsot name
            if (pos != -1) {
                uri = "";
            } else {
                uri = uri.substring(pos);
            }

            String match = ";jessionid=";
            int semicolon = uri.indexOf(match);
            if (semicolon >= 0) {
                String rest = uri.substring(semicolon + match.length());
                int semicolon2 = rest.indexOf(';');
                if (semicolon2 >= 0) {
                    request.setRequestedSessionId(rest.substring(0, semicolon2));
                    rest = rest.substring(semicolon2);
                } else {
                    request.setRequestedSessionId(rest);
                    rest = "";
                }
                request.setRequestedSessionURL(true);
                uri = uri.substring(0, semicolon) + rest;
            } else {
                request.setRequestedSessionId(null);
                request.setRequestedSessionURL(false);
            }

            //Normalize URI
            String normalizedUri = normalize(uri);

            //Set the corresponding request properties
            ((HttpRequest) request).setMethod(method);
            request.setProtocol(protocol);
            if (normalizedUri != null) {
                ((HttpRequest) request).setRequestURI(normalizedUri);
            } else {
                ((HttpRequest) request).setRequestURI(uri);
            }

            if (normalizedUri == null) {
                throw new ServletException("Invalid URI: " + uri + "'");
            }
        }
    }

    /**
     * normalize处理化
     * @param uri
     * @return
     */
    private String normalize(String uri) {
        return null;
    }

    /**
     * 头解析
     * 循环读入Io流，直到所有头部读取完成
     * @param input 输入流
     */
    private void parseHeaders(SocketInputStream input) throws ServletException {

        HttpHeader header = null;
        for (header = new HttpHeader(); input.readHeader(header); header = new HttpHeader()) {

            if (header.nameEnd == 0) {
                if (header.valueEnd == 0) {
                    return;
                } else {
                    throw new ServletException("Parse Header errror!");
                }
            }

            String name = new String(header.name, 0, header.nameEnd);
            String value = new String(header.value, 0, header.valueEnd);

            //加入HashMap
            request.addHeader(name, value);

            if (name.equals("cookie")) {
                parseCookie(value);
            } else if (name.equals("content-length")) {
                int n = -1;
                try {
                    n = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    throw new ServletException(("Parse Header Content Length Exception!"));
                }
                request.setContentLength(n);
            } else if (name.equals("content-type")) {
                request.setContentType(value);
            }
        }

    }

    private void parseCookie(String value) {
        Cookie cookies[] = RequestUtil.parseCookieHeader(value);
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals("jsessionid")) {
                //Accept only the first session id cookie
                request.setRequestedSessionId(cookies[i].getValue());
                request.setRequestedSessionCookie(true);
                request.setRequestedSessionURL(false);
            }
            request.addCookie(cookies[i]);
        }
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 工作委派
     * @param socket
     */
    public synchronized void assign(Socket socket) {
        /**
         * 判断是否有新socket接入
         * 如果有新socket，进入work等待
         */
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        this.scoket = socket;
        available = true;
        notifyAll();
    }

    /**
     * work等待
     * @return
     */
    private synchronized Socket await() {
        /**
         * 若无新socket接入
         * 则等待
         */
        while(!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Socket socket = this.scoket;
        available = false;
        notifyAll();
        return socket;
    }

    @Override
    public void run() {

        while(!httpConnector.stopped) {

            Socket socket = await();
            if(socket == null)
                continue;
            try {
                process(socket);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
