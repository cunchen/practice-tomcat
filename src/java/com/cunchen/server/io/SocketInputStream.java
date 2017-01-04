package com.cunchen.server.io;

import com.cunchen.server.header.HttpHeader;

import java.io.InputStream;

/**
 * SocketInput流包装
 * Created by wqd on 2016/12/29.
 */
public class SocketInputStream {

    private InputStream inputStream;

    public SocketInputStream(InputStream inputStream) {
        this(inputStream, 2048);
    }

    public SocketInputStream(InputStream inputStream, int buffersize) {

    }

    public void readRequestLine(RequestLine requestLine) {
    }

    public void readHeader(HttpHeader header) {

    }
}
