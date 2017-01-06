package com.cunchen.server.io;

import com.cunchen.connector.ResponseStream;
import com.cunchen.connector.ResponseWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Response
 * Created by wqd on 2016/12/29.
 */
public class HttpResponse extends Response implements ServletResponse {

    private OutputStream outputStream;
    private ServletRequest request;

    private PrintWriter writer;

    public HttpResponse(OutputStream outputStream) {
        super(outputStream);
        this.outputStream = outputStream;
    }

    public void setRequest(ServletRequest request) {
        this.request = request;
    }

    public void setHeader(String server, String s) {
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return (ServletOutputStream) request;
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

}
