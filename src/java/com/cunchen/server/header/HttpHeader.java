package com.cunchen.server.header;

/**
 * Http请求头部
 *
 * NameEnd/ValueEnd == 0标记为未读取
 * Created by wqd on 2017/1/4.
 */
public class HttpHeader {

    public char[] name;
    public int nameEnd;

    public char[] value;
    public int valueEnd;
}
