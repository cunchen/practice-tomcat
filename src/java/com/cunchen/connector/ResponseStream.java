package com.cunchen.connector;

import com.cunchen.server.io.HttpResponse;

import javax.servlet.ServletOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 输出流包装类
 * Created by wqd on 2017/1/5.
 */
public class ResponseStream extends ServletOutputStream{

    private boolean commit;

    private BufferedOutputStream outputStream;

    private final int BUFFERER_SIZE = 2048;

    public ResponseStream(HttpResponse httpResponse) throws IOException {
        this(httpResponse.getOutputStream());
    }

    public ResponseStream(OutputStream outputStream) {
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }
}
