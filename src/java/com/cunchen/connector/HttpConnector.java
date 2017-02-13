package com.cunchen.connector;

import org.apache.tomcat.util.net.AbstractEndpoint;
import org.apache.tomcat.util.net.DefaultServerSocketFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

/**
 * Created by wqd on 2016/12/28.
 */
public class HttpConnector implements Runnable{

    private boolean stopped;

    private final String scheme = "http";

    private int port;                   //端口
    private int backlog;                //等待请求数
    private InetAddress ifAddress;      //地址

    protected int minProcessors = 5;    //最小连接数
    private int maxProcessors = 20;     //最大连接数,若取负数，则无上限
    private int curProcessors = 0;      //当前连接数

    private Stack<HttpProcessor> processors;    //Processor连接池

    private int bufferSize;                     //缓冲区大小

    public String getScheme() {
        return scheme;
    }

    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stopped) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();

                //判断是否由于端口映射造成的二次访问
                if(socket.getLocalPort() != port)
                    continue;

            } catch (IOException e) {
                e.printStackTrace();
            }

            HttpProcessor processor = createProcessor();
            processor.assign(socket);
        }
    }

    /**
     * TODO
     * 从栈中获取HttpProcessor实例
     * 若请求已超过最大数量，则返回空
     * @return
     */
    private HttpProcessor createProcessor() {
        return null;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();

        while(curProcessors < minProcessors) {
            if((maxProcessors > 0) && (curProcessors >= maxProcessors))
                break;
            HttpProcessor processor = new HttpProcessor(this);
            recycle(processor);
        }
    }

    /**
     * processor回收
     */
    void recycle(HttpProcessor processor) {
        processors.push(processor);
    }

    /**
     * 初识化
     */
    public void initialize() throws IOException {
        ServerSocket serverSocket = open();
    }

    /**
     *
     */
    private ServerSocket open() throws IOException {
        AbstractEndpoint<?> endPoint = null;
        DefaultServerSocketFactory dsf = new DefaultServerSocketFactory(endPoint);
        return dsf.createSocket(port, backlog, ifAddress);
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
