package com.cunchen;

/**
 * Container
 * Created by admin on 2017/7/31.
 */
public interface Container {

    //添加子容器
    public void addChild(Container child);

    //移除子容器
    public void removeChild(Container child);

    //查找子容器
    public Container findChild(String childName);

    //子容器列表
    public Container[] findChildren();

    //获取父容器
    public Container getParent();

    //设置父容器
    public void setParent(Container parent);

    //获取容器名称
    public String getName();


    //设置容器名称
    public void setName(String name);

    //设置加载器
    public void setLoader(Loader loader);

    //获取加载器
    public Loader getLoader();
}
