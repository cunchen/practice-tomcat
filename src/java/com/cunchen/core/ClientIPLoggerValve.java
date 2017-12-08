package com.cunchen.core;


import com.cunchen.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * 客户端IP记录阀
 * Created by wqd on 2017/3/9.
 */
public class ClientIPLoggerValve implements Valve, Contained {

    protected Container container;

    protected String info;

    private Logger log = Logger.getLogger(info);

    @Override
    public Container getContainer() {
        return this.container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public String getInfo() {
        return info;
    }

    /**
     * 代理方法
     * @param request {@link Request}
     * @param response {@link Response}
     * @param valveContext {@link ValveContext}
     * @throws IOException Valve.invoke
     * @throws ServletException Valve.invoke
     */
    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {

        valveContext.invokeNext(request, response);


        ServletRequest request1 = request.getRequest();
        if(request1 instanceof HttpServletRequest) {
            HttpServletRequest hreq = (HttpServletRequest) request1;
            Enumeration headerNames = hreq.getHeaderNames();
            while(headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement().toString();

                String headerValue = hreq.getHeader(headerName);

                log.info(getInfo() + "recorder-----" + headerName + ":"  + headerValue);
            }
        }

    }

}
