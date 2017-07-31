package com.cunchen.connector;

import com.cunchen.Request;
import org.apache.catalina.*;
import org.apache.catalina.Response;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * RequsetBase项目
 * Created by wqd on 2017/2/13.
 */
public class RequestBase implements Request,ServletRequest {

    private RequestStream requestStream;

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

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
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public String getAuthorization() {
        return null;
    }

    @Override
    public void setAuthorization(String authorization) {

    }

    @Override
    public Connector getConnector() {
        return null;
    }

    @Override
    public void setConnector(Connector connector) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public ServletRequest getRequest() {
        return null;
    }

    @Override
    public Response getResponse() {
        return null;
    }

    @Override
    public void setResponse(Response response) {

    }

    @Override
    public Socket getSocket() {
        return null;
    }

    @Override
    public void setSocket(Socket socket) {

    }

    @Override
    public InputStream getStream() {
        return null;
    }

    @Override
    public void setStream(InputStream stream) {

    }

    @Override
    public Wrapper getWrapper() {
        return null;
    }

    @Override
    public void setWrapper(Wrapper wrapper) {

    }

    @Override
    public ServletInputStream createInputStream() throws IOException {
        return null;
    }

    @Override
    public void finishRequest() throws IOException {

    }

    @Override
    public Object getNote(String name) {
        return null;
    }

    @Override
    public Iterator getNoteNames() {
        return null;
    }

    @Override
    public void recycle() {

    }

    @Override
    public void removeNote(String name) {

    }

    @Override
    public void setContentLength(int length) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setNote(String name, Object value) {

    }

    @Override
    public void setProtocol(String protocol) {

    }

    @Override
    public void setRemoteAddr(String remote) {

    }

    @Override
    public void setScheme(String scheme) {

    }

    @Override
    public void setSecure(boolean secure) {

    }

    @Override
    public void setServerName(String name) {

    }

    @Override
    public void setServerPort(int port) {

    }
}
