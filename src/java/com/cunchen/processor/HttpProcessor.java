package com.cunchen.processor;

import com.cunchen.connector.HttpConnector;
import com.cunchen.server.io.HttpRequest;
import com.cunchen.server.io.HttpResponse;
import com.cunchen.server.io.SocketInputStream;
import com.cunchen.server.processor.ServletProcessor;
import com.cunchen.server.processor.StaticResourceProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wqd on 2016/12/28.
 */
public class HttpProcessor {

    private HttpConnector httpConnector ;
    private HttpRequest request;
    private HttpResponse response;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            request = new HttpRequest(input);
            request.parse();

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Io流Request解析
     * @param input 输入流
     * @param output 输出流
     */
    private void parseRequest(SocketInputStream input, OutputStream output) {


    }

    /**
     * 头解析
     * @param inputStream 输入流
     */
    private void parseHeaders(SocketInputStream inputStream) {

    }
}
