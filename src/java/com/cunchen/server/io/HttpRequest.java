package com.cunchen.server.io;

import com.cunchen.util.RequestUtil;
import org.apache.catalina.util.ParameterMap;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Http请求类
 * Created by wqd on 2016/12/29.
 */
public class HttpRequest implements ServletRequest {

    protected ParameterMap<String, String> headers = new ParameterMap();

    protected ArrayList<Cookie> cookies = new ArrayList();

    protected ParameterMap parameters = null;

    private SocketInputStream input ;

    private String uri;

    /**
     * 请求问号后边的参数
     */
    private String queryString;

    private String requestedSessionId;

    private boolean requestedSessionURL;
    private String method;
    private String protocol;
    private String requestURI;
    private int contentLength;
    private String contentType;
    private boolean requestedSessionCookie;

    //标记是否已被解析
    private boolean parsed;

    public HttpRequest(SocketInputStream input) {
        this.input = input;
    }

    /**
     * 解析
     */
    public void parse() {

    }

    /**
     * 参数解析
     */
    public void parseParameters() {
        if(parsed)
            return;
        ParameterMap results = parameters;
        if(results == null)
            results = new ParameterMap();
        results.setLocked(false);
        String encoding = getCharacterEncoding();
        if(encoding == null)
            encoding = "ISO-8859-1";

        String queryString = getQueryString();

        RequestUtil.parseParameters(results, queryString, encoding);
    }

    /**
     * URI解析
     */
    public void parseUri() {

    }

    /**
     * 获取解析后uri
     * 需要先调用 parse()方法
     * @return String Uri字符
     */
    public String getRequestURI() {
        return uri;
    }

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
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }


    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryString( ) { return  queryString; }

    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    public void setRequestedSessionURL(boolean requestedSessionURL) {
        this.requestedSessionURL = requestedSessionURL;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    /**
     * 将Header内容读入
     * @param name HeaderName
     * @param value HeaderValue
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setRequestedSessionCookie(boolean requestedSessionCookie) {
        this.requestedSessionCookie = requestedSessionCookie;
    }

    //TODO
    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }
}
