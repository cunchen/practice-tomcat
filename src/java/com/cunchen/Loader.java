package com.cunchen;

/**
 * Loader
 * Created by qidong on 2017/9/7.
 */
public interface Loader {


    //获取类加载器
    public ClassLoader getClassLoader();

    /**
     * 获取容器
     * @return Container 关联容器
     */
    public Container getContainer();


    /**
     * 设置关联容器
     *
     * @param container 关联容器
     */
    public void setContainer(Container container);
}
