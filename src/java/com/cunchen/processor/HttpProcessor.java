package com.cunchen.processor;

import com.cunchen.connector.HttpConnector;
import com.cunchen.server.io.Request;
import com.cunchen.server.io.Response;
import com.cunchen.server.processor.ServletProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wqd on 2016/12/28.
 */
public class HttpProcessor {

    private HttpConnector httpConnector ;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            Request request = new Request(inputStream);
            request.parse();

            outputStream = socket.getOutputStream();
            Response response = new Response(outputStream);

            String uri = request.getUri();

            if(uri.)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
