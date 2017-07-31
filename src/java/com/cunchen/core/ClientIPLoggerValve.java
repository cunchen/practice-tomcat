package com.cunchen.core;


import com.cunchen.Contained;
import org.apache.catalina.*;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 客户端IP记录阀
 * Created by wqd on 2017/3/9.
 */
public class ClientIPLoggerValve implements Valve, Contained {

    protected Container container;

    @Override
    public Container getContainer() {
        return null;
    }

    @Override
    public void setContainer(Container container) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {

    }

}
