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

    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public int getNameEnd() {
        return nameEnd;
    }

    public void setNameEnd(int nameEnd) {
        this.nameEnd = nameEnd;
    }

    public char[] getValue() {
        return value;
    }

    public void setValue(char[] value) {
        this.value = value;
    }

    public int getValueEnd() {
        return valueEnd;
    }

    public void setValueEnd(int valueEnd) {
        this.valueEnd = valueEnd;
    }
}
