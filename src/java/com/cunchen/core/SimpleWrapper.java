package com.cunchen.core;

import com.cunchen.*;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Wrapper接口实现
 * Created by cunchen on 2017/3/9.
 */
public class SimpleWrapper extends ContainerBase implements Wrapper, Pipeline{

    private Loader loader;
    protected Container parent;


    private boolean unuploading;


    //Pipeline
    private SimplePipeline pipeline = new SimplePipeline(this);

    /**
     * Servlet服务模式
     * 只能同时响应一条请求
     *
     * 如果为True，你不能在servlet service中同时响应两条请求。servlet容器
     * 将强制servlet不会同时响应两条请求。Servlet容器可以确保请求同步访问
     * 一个单例的servlet实例，或者将每一个新的请求分配到一个servlet池中的
     * 空闲的servlet实例
     */
    private volatile boolean singleThreadModel;

    private SimpleWrapper() {

        getPipeline().setBasic(new SimpleWrapperValve());
    }

    public SimplePipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(SimplePipeline pipeline) {
        this.pipeline = pipeline;
    }


    @Override
    public void addChild(Container child) {

    }

    @Override
    public void removeChild(Container child) {

    }

    @Override
    public Container findChild(String childName) {
        return null;
    }

    @Override
    public Container[] findChildren() {
        return new Container[0];
    }

    @Override
    public Container getParent() {
        return null;
    }

    @Override
    public void setParent(Container parent) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setBasic(Valve basic) {

    }

    @Override
    public Valve getBasic() {
        return null;
    }

    @Override
    public void addValve(Valve valve) {

    }

    @Override
    public void remove(Valve valve) {

    }

    @Override
    public Valve[] getValves() {
        return new Valve[0];
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {

    }

    /**
     * @return
     * @throws ServletException
     */
    @Override
    public Servlet allocate() throws ServletException {

        //If unuploading this servlet, throw an exception
        if(unuploading) {
            throw new ServletException("standardWrapper.unloading" + getName());
        }


        if(!singleThreadModel) {

        }

        return null;
    }

    /**
     * 加载Servlet
     * @return 加载Servlet
     */
    public synchronized Servlet loadServlet() {

        if(!singleThreadModel) {

        }

        return null;
    }



    //静态类单例模式
    private SimpleWrapper getInstance() {
        return SingletonInstance.ISNTANCE;
    }

    //静态内部类
    private static class SingletonInstance {

        private static SimpleWrapper ISNTANCE = new SimpleWrapper();
    }
}
