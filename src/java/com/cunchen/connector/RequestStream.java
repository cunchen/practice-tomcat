package com.cunchen.connector;

import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * RequestStream
 * Created by wqd on 2017/2/13.
 */
public class RequestStream extends ServletInputStream {

    @Override
    public int read() throws IOException {
        return 0;
    }
}
