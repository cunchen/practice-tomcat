package com.cunchen.server.io;

import com.cunchen.Constants;
import com.cunchen.server.header.HttpHeader;

import javax.servlet.ServletException;
import java.io.*;

/**
 * inputStream流包装类
 * Created by wqd on 2016/12/29.
 */
public class SocketInputStream {

    private BufferedReader streamReader;

    public SocketInputStream(InputStream inputStream) {
        this(inputStream, 2048);
    }

    public SocketInputStream(InputStream inputStream, int buffersize) {

        this.streamReader = new BufferedReader(new InputStreamReader(inputStream), buffersize);

    }

    /**
     * IO流解析Http第一行
     * 解析Io流第一行，并将结果封装如requestLine，包括
     * requestString 原始请求字符串
     * method 请求方法
     * uri 请求uri
     * protocol 请求协议
     * @param requestLine requestLine POJO类
     */
    public void readRequestLine(RequestLine requestLine) {
        try {
            String line = streamReader.readLine();

            requestLine.setRequestString(line);

            String[] results = line.split(Constants.PUNCATUATION_BLACK);

            if(results.length != 3) {
                throw new ServletException("The request header is error!");
            } else {
                requestLine.setMethod(results[0].toCharArray());
                requestLine.setMethodEnd(results[0].length());

                requestLine.setUri(results[1].toCharArray());
                requestLine.setUriEnd(results[1].length());

                requestLine.setProtocol(results[2].toCharArray());
                requestLine.setProtocoEnd(results[2].length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析Request Header
     * 读入一行RequestHeader，对RequestHeader进行解析，并将结果
     * 封装到R {@link HttpHeader} 实体类中，返回结果用于判断是
     * 否成功读取下一行内容
     * @param header HttpHeader类，用于存储header解析结果
     * @return 返回boolean类型，ture为有结果，false为已经读取完毕
     */
    public boolean readHeader(HttpHeader header) {
        try {
            String line = streamReader.readLine();
            if(line == null || line.isEmpty()) {
                return false;
            }
            int colon = line.indexOf(Constants.PUNCATUATION_COLON);

            if(colon > 0) {
                String name = line.substring(0, colon).trim();
                header.setName(name.toCharArray());
                header.setNameEnd(name.length());

                String value = line.substring(colon + 1, line.length()).trim();
                header.setValue(value.toCharArray());
                header.setValueEnd(value.length());
                return true;
            } else {
                throw new ServletException("Request header parse error!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return false;
    }
}
