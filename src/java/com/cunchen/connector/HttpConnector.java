package com.cunchen.connector;

import com.cunchen.processor.HttpProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

/**
 * HttpConnector
 * Created by wqd on 2016/12/28.
 */
public class HttpConnector implements Runnable{

    boolean stopped;

    private final String scheme = "http";

    private int port;                   //端口
    private int backlog;                //等待请求数
    private InetAddress ifAddress;      //地址

    protected int minProcessors = 5;    //最小连接数
    private int maxProcessors = 20;     //最大连接数,若取负数，则无上限
    private int curProcessors = 0;      //当前连接数

    private Stack<HttpProcessor> processors;    //Processor连接池

    private int bufferSize;                     //缓冲区大小

    private Object threadSync = new Object();

    public String getScheme() {
        return scheme;
    }

    public HttpConnector(){
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接器连接操作
     * 创建ServerSocket
     * 接入请求
     * 调用Prossor进行处理
     */
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

                //判断是否由于端口映射造成的二次访问以及socket空问题
                if(socket.getLocalPort() != port || socket == null)
                    continue;

            } catch (IOException e) {
                e.printStackTrace();
            }

            HttpProcessor processor = createProcessor();
            if(processor == null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            processor.assign(socket);

            synchronized (threadSync) {
                threadSync.notifyAll();
            }
        }

    }

    /**
     * 创建Processor
     * 若对象池中有Processor，则获取
     * 若当先实例数小于最大实例数，则创建新实例
     * 返回空
     * @return
     */
    private HttpProcessor createProcessor() {
        synchronized (processors) {
            if (processors.size() > 0) {
                return ((HttpProcessor) processors.pop());
            }

            if ((minProcessors > 0) && (curProcessors < maxProcessors)) {
                return newProcessor();
            } else {
                if (maxProcessors < 0) {
                    return newProcessor();
                } else {
                    return (null);
                }
            }
        }
    }

    /**
     * 实例化Processor
     * @return HttpProcessor
     */
    private HttpProcessor newProcessor() {
        HttpProcessor processor = new HttpProcessor(this, curProcessors++);
        processors.push(processor);
        return processor;
    }


    private void process(Socket socket) {

    }

    /**
     * 启动
     * 分配线程启动Connector
     * 初始化
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();

        /**
         * 初识化processors线程池
         */
        while(curProcessors < minProcessors) {
            if((maxProcessors > 0) && (curProcessors >= maxProcessors))
                break;
            HttpProcessor processor = createProcessor();
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
    private void initialize() throws IOException {
        ServerSocket serverSocket = open();
        processors = new Stack<>();
    }

    /**
     * 开启ServerSocket
     */
    private ServerSocket open() throws IOException {
        return new ServerSocket(port, backlog, ifAddress);
    }
}
