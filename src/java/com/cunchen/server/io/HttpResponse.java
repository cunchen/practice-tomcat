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

    private final String HEAD = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
//            "Content-Length: 60\n\n" +
            "\r\n" ;

    private final String PAGE_404 = "HTTP/1.1 404 File Not Found\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: 30\n\n" +
            "\r\n" +
            "<h1>找不到小仙女啦~_~</h1>";

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
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                    String body = new String(bytes);
                    outputStream.write((HEAD + body).getBytes());
                }
            } else {
                writer = new ResponseWriter(new OutputStreamWriter(outputStream,getCharacterEncoding()));
                writer.write(PAGE_404);
                writer.flush();
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
        return "UTF-8";
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ResponseStream(outputStream);
    }

    /**
     * 获取输出PrintWriter
     * @return
     * @throws IOException
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        OutputStreamWriter osr = new OutputStreamWriter(outputStream, getCharacterEncoding());
        this.writer = new ResponseWriter(osr);
        writer.write(HEAD);
        return writer;
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
        if(writer != null) {
            writer.flush();
            writer.close();
        }

        try {
            if(outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
