package com.cunchen.connector;

import com.cunchen.processor.HttpProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HttpConnector
 * Created by wqd on 2016/12/28.
 */
public class HttpConnector implements Runnable{

    boolean stopped;

    private final String scheme = "http";

    Object threadSync;

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

                //判断是否由于端口映射造成的二次访问以及socket空问题
                if(socket.getLocalPort() != port || socket == null)
                    continue;

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                process(socket);
            } catch (Throwable t) {
                System.out.println("process.invoke");;
            }

            connector.recycle(this);

            synchronized (threadSync) {
                threadSync.notifyAll();
            }
        }

    }


    private void process(Socket socket) {

    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
