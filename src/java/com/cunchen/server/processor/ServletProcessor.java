package com.cunchen.server.processor;

import com.cunchen.Constants;
import com.cunchen.connector.HttpRequest;
import com.cunchen.connector.HttpRequestFacade;
import com.cunchen.connector.HttpResponse;
import com.cunchen.connector.HttpResponseFacade;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Servlet处理器
 * Created by wqd on 2016/12/26.
 */
public class ServletProcessor {

    /**
     * 动态Servlet处理
     * @param request 请求 处理
     * @param response 结果处理
     */
    public void process(HttpRequest request, HttpResponse response) {
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;


        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;

            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            HttpRequestFacade requestFacde = new HttpRequestFacade(request);
            HttpResponseFacade responseFacde = new HttpResponseFacade((HttpServletResponse) response);
            servlet.service(requestFacde, responseFacde);
            ((HttpResponse) response).finishResponse();
        } catch (ServletException | IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
