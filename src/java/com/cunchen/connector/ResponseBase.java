package com.cunchen.connector;

import com.cunchen.Response;
import org.apache.catalina.*;
import org.apache.catalina.Request;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * ResponseBase实现
 * Created by wqd on 2017/2/13.
 */
public class ResponseBase implements Response, ServletResponse {

    private ResponseStream responseStream ;

    @Override
    public String getCharacterEncoding() {
        return null;
    }


    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;
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
    public Connector getConnector() {
        return null;
    }

    @Override
    public void setConnector(Connector connector) {

    }

    @Override
    public int getContentCount() {
        return 0;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public void setAppCommitted(boolean appCommitted) {

    }

    @Override
    public boolean isAppCommitted() {
        return false;
    }

    @Override
    public boolean getIncluded() {
        return false;
    }

    @Override
    public void setIncluded(boolean included) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public void setRequest(Request request) {

    }

    @Override
    public ServletResponse getResponse() {
        return null;
    }

    @Override
    public OutputStream getStream() {
        return null;
    }

    @Override
    public void setStream(OutputStream stream) {

    }

    @Override
    public void setSuspended(boolean suspended) {

    }

    @Override
    public boolean isSuspended() {
        return false;
    }

    @Override
    public void setError() {

    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public ServletOutputStream createOutputStream() throws IOException {
        return null;
    }

    @Override
    public void finishResponse() throws IOException {

    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public PrintWriter getReporter() {
        return null;
    }

    @Override
    public void recycle() {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public void sendAcknowledgement() throws IOException {

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
