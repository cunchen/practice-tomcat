package com.cunchen.server.io;

import com.cunchen.Constants;
import com.cunchen.connector.ResponseStream;
import com.cunchen.connector.ResponseWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Response
 * Created by wqd on 2016/12/29.
 */
public class HttpResponse implements ServletResponse {

    private final static int BUFFER_SIZE = 2048;

    private OutputStream outputStream;
    private HttpRequest request;

    private PrintWriter writer;

    private Charset charset;

    private final String head = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
//            "Content-Length: 60\n\n" +
            "\r\n" ;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public void setHeader(String server, String s) {
    }


    public void sendStaticResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        if(request == null)
            return;
        File file = new File(Constants.WEB_ROOT, request.getUri());
        try {
            if(file.isFile()) {
                writer = getWriter();
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                    String body = new String(bytes);
                    outputStream.write((head + body).getBytes());
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\n\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if( fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    @Override
    public String getCharacterEncoding() {
        return "utf-8";
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return (ServletOutputStream) outputStream;
    }

    /**
     * 获取输出PrintWriter
     * @return
     * @throws IOException
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        ResponseStream newStream = new ResponseStream(this);
        newStream.setCommit(false);
        OutputStreamWriter osr = new OutputStreamWriter(newStream, getCharacterEncoding());
        this.writer = new ResponseWriter(osr);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    public void finishResponse() {

    }
}
