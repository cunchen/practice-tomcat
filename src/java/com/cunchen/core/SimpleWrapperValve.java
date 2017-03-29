package com.cunchen.core;

import com.cunchen.Request;
import com.cunchen.Response;
import com.cunchen.Valve;
import com.cunchen.ValveContext;
import org.apache.catalina.Contained;
import org.apache.catalina.Container;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SimpleSrapperValue
 * 将 ServletRequest 和 ServletResponse 转化为　HttpRequest 和 HttpResponse
 * 实例化Servlet 调用 Servlet.service(Request, Response)
 * Created by wqd on 2017/3/9.
 */
public class SimpleWrapperValve implements Valve, Contained {
    @Override
    public String getInfo() {
        return null;
    }


    /**
     * 代理 Servlet.serice方法
     * @param request Request
     * @param response Resposne
     */
    @Override
    public void invoke(Request request, Response response, ValveContext context)  {
        SimpleWrapper wrapper = (SimpleWrapper) getContainer();
        ServletRequest sreq = request.getRequest();
        ServletResponse sres = response.getResponse();
        Servlet servlet = null;

        HttpServletRequest hreq = null;
        if(sreq instanceof HttpServletRequest)
            hreq = (HttpServletRequest) sreq;

        HttpServletResponse hres = null;
        if(sres instanceof HttpServletResponse)
            hres = (HttpServletResponse) sres;

        //Allocate a servlet instance to process this request
        try {
            servlet = wrapper.allocate();
            if (hres != null && hreq != null) {
                servlet.service(hreq, hres);
            } else {
                servlet.service(sreq, sres);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Container getContainer() {
        return null;
    }

    @Override
    public void setContainer(Container container) {

    }
}
