package com.cunchen.core;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Valve;
import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

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
    public Valve getNext() {
        return null;
    }

    @Override
    public void setNext(Valve valve) {

    }

    @Override
    public void backgroundProcess() {

    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
    }

    @Override
    public void event(Request request, Response response, CometEvent cometEvent) throws IOException, ServletException {

    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }
}
