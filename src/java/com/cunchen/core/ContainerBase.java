package com.cunchen.core;

import com.cunchen.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

/**
 * ContainerBase
 * Created by admin on 2017/7/31.
 */
public abstract class ContainerBase implements Container, Pipeline {

    /**
     * 子容器存储
     */
    protected HashMap<String, Container> children = new HashMap();

    /**
     * Name
     */
    protected String name ;

    /**
     * 父容器
     */
    protected Container parent;

    /**
     * Pipeline实例
     */
    protected Pipeline pipeline = new StandardPipeline();

    /**
     * Loader
     */
    protected Loader loader;


    /**
     * 添加子容器
     * @param child Container
     */
    @Override
    public void addChild(Container child) {

        children.put(child.getName() ,child);
    }

    /**
     * 移除子容器
     * @param child Container
     */
    @Override
    public void removeChild(Container child) {
        children.remove(child);
    }

    /**
     * 查找子容器
     * @param childName
     */
    @Override
    public Container findChild(String childName) {
        return (Container) children.get(childName);
    }

    /**
     * 获取所有子容器
     * @return Contianer[]
     */
    @Override
    public Container[] findChildren() {
        return (Container[]) children.values().toArray();
    }

    /**
     * 设置父容器
     * @param parent Container
     */
    @Override
    public void setParent(Container parent) {
        this.parent.removeChild(this);
        this.parent = parent;
    }

    /**
     * 获取父容器
     * @return Container
     */
    @Override
    public Container getParent() {
        return parent;
    }

    /**
     * 获取容器名称
     * @return String name
     */
    @Override
    public String getName() {
        return this.name;
    }


    /**
     * 设置容器名称
     */
    @Override
    public void setName(String name) {
        this.parent.removeChild(this);
        this.name = name;
        this.parent.addChild(this);
    }

    //设置基本阀门
    public void setBasic(Valve basic) {
        pipeline.setBasic(basic);
    }

    //获取基本阀门
    public Valve getBasic() {
        return pipeline.getBasic();
    }

    //添加阀门
    public void addValve(Valve valve) {
        pipeline.addValve(valve);
    }

    //删除阀门
    public void remove(Valve valve) {
        pipeline.remove(valve);
    }

    //获得所有非基本阀门
    public Valve[] getValves(){
        return pipeline.getValves();
    }

    //代理
    public void invoke(Request request, Response response) throws IOException, ServletException {
        pipeline.invoke(request,response);
    }


    /**
     * 设置loader
     * @param loader 加载器
     */
    @Override
    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    /**
     * 获取类加载器
     * @return Loader 加载器
     */
    @Override
    public Loader getLoader() {
        if (loader != null)
            return (loader);
        if (parent != null)
            return (parent.getLoader());
        return (null);
    }
}
