package com.cunchen.connector;

import com.cunchen.processor.HttpProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wqd on 2016/12/28.
 */
public class HttpConnector implements Runnable{

    boolean stopped;

    private final String scheme = "http";

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
            } catch (IOException e) {
                e.printStackTrace();
            }

            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
