package com.cunchen.connector;

import com.cunchen.Lifecycle;
import com.cunchen.LifecycleException;
import com.cunchen.LifecycleListener;
import com.cunchen.util.LifecycleSupport;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

/**
 * HttpConnector
 * Created by wqd on 2016/12/28.
 */
public class HttpConnector implements Runnable, Lifecycle{

    volatile boolean stopped;

    volatile boolean started;

    protected LifecycleSupport lifecycle = new LifecycleSupport(this);

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

    private String threadName;           //thread 名称

    private ServerSocket serverSocket = null;
    private Thread thread = null;

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
        new Thread(processor).start();
        return processor;
    }


    private void process(Socket socket) {

    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycle.addLiffecycleListener(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycle.findLifecycleListeners();
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycle.removeLifecycleListener(listener);
    }

    /**
     * 启动
     * 分配线程启动Connector
     * 初始化
     */
    public void start() throws LifecycleException {

        // Validate and update our current state
        if (started)
            throw new LifecycleException
                    ("httpConnector.alreadyStarted");
        lifecycle.fireLifecycleEvent(BEFORE_START_EVENT, null);

        threadName = "HttpConnector[" + port + "]";

        started = true;
        stopped = false;

        lifecycle.fireLifecycleEvent(START_EVENT, null);
        threadStart();

        /**
         * 初识化processors线程池
         */
        while(curProcessors < minProcessors) {
            if((maxProcessors > 0) && (curProcessors >= maxProcessors))
                break;
            HttpProcessor processor = createProcessor();
            recycle(processor);
        }

        lifecycle.fireLifecycleEvent(AFTER_START_EVENT, null);
    }

    @Override
    public void stop() throws LifecycleException {

        lifecycle.fireLifecycleEvent(Lifecycle.BEFORE_STOP_EVENT, null);

        if(!started)
            throw new LifecycleException();


        lifecycle.fireLifecycleEvent(Lifecycle.STOP_EVENT, null);

        started = false;

        for (int i = processors.size() - 1; i >= 0 ; i++) {
            HttpProcessor processor = processors.pop();
            if(processor instanceof Lifecycle) {
                try {
                    ((Lifecycle) processor).stop();
                } catch (LifecycleException e) {
                    System.out.println("HttpProcessor stop" + e);
                }
            }
        }

        synchronized (threadSync) {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            threadStop();
        }
        serverSocket = null;

    }

    /**
     * 线程启动
     */
    public void threadStart() {
        thread = new Thread(this, threadName);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 线程关闭
     */
    private void threadStop() {
        stopped =true;

        try {
            threadSync.wait();
        } catch (InterruptedException e) {

        }
        thread = null;
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
        started = false;
    }

    /**
     * 开启ServerSocket
     */
    private ServerSocket open() throws IOException {
        return new ServerSocket(port, backlog, ifAddress);
    }
}
