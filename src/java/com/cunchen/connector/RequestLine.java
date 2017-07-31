package com.cunchen.connector;

/**
 * 请求行包装类
 * Created by wqd on 2017/1/3.
 */
public class RequestLine {

    private String requestString;

    public char[] uri;

    public char[] method;

    public int methodEnd;

    public int uriEnd;
    private char[] protocol;
    private int protocoEnd;

    public int indexOf(String s) {
        return requestString.indexOf(s);
    }

    public void setUri(char[] uri) {
        this.uri = uri;
    }

    public void setMethod(char[] method) {
        this.method = method;
    }

    public void setMethodEnd(int methodEnd) {
        this.methodEnd = methodEnd;
    }

    public void setUriEnd(int uriEnd) {
        this.uriEnd = uriEnd;
    }

    public void setProtocol(char[] protocol) {
        this.protocol = protocol;
    }

    public void setProtocoEnd(int protocoEnd) {
        this.protocoEnd = protocoEnd;
    }

    public String getRequestString() {
        return requestString;
    }

    public void setRequestString(String requestString) {
        this.requestString = requestString;
    }
}
