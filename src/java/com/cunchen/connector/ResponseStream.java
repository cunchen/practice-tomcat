package com.cunchen.connector;

import com.cunchen.server.io.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 输出流包装类
 * Created by wqd on 2017/1/5.
 */
public class ResponseStream extends OutputStream{


    private boolean commit;

    public ResponseStream(HttpResponse httpResponse) {
    }

    @Override
    public void write(int b) throws IOException {
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }
}
