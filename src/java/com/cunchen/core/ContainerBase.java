package com.cunchen.core;

import com.cunchen.Container;

import java.util.HashMap;

/**
 * ContainerBase
 * Created by admin on 2017/7/31.
 */
public abstract class ContainerBase implements Container {

    /**
     * 子容器存储
     */
    private HashMap<String, Container> children = new HashMap();

    /**
     * Name
     */
    private String name ;

    /**
     * 父容器
     */
    private Container parent;


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
}
