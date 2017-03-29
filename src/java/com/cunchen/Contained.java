package com.cunchen;

import org.apache.catalina.Container;

/**
 * Contained
 * Created by wqd on 2017/3/9.
 */
public interface Contained {

    public Container getContainer();

    public void setContainer(Container container);

}
