package com.cunchen;

import com.cunchen.server.io.Request;
import com.cunchen.server.io.Response;
import com.cunchen.server.processor.ServletProcessor;
import com.cunchen.server.processor.StaticResourceProcessor;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  TomCat
 * Created by wqd on 2016/12/22.
 */
public class HttpServer {

    private final static int BUFFER_SIZE = 1024;
    public final static String WEB_ROOT = System.getProperty("user.dir") + File.separator + "out" + File.separator + "webroot";
    private final static String SHUTDOWN_COMMEND = "/shutdown";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
//        ServerSocket serverSocket = null;
//        int port = 8080;
//        try {
//            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        while(!shutdown) {
//            Socket socket = null;
//            InputStream input = null;
//            OutputStream output = null;
//
//            try {
//                socket = serverSocket.accept();
//                input = socket.getInputStream();
//                output = socket.getOutputStream();
//
//                if(input == null)
//                    continue;
//
//                Request request = new Request(input);
//                request.parse();
//
//                if(request.getUri() == null)
//                    continue;
//
//                Response response = new Response(output);
//                response.setRequest(request);
//
//                if(request.getUri().startsWith("/servlet/")) {
//                    ServletProcessor processor = new ServletProcessor();
//                    processor.process(request, response);
//                } else {
//                    StaticResourceProcessor processor = new StaticResourceProcessor();
//                    processor.process(request, response);
//                }
//
//                response.sendStaticResource();
//                socket.close();
//
//                shutdown = request.getUri().equals(SHUTDOWN_COMMEND);
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.exit(1);
//            }
//        }
    }
}
