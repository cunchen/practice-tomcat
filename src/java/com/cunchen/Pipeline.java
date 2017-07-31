package com.cunchen;

import org.apache.catalina.Container;

/**
 * Pipeline
 * Created by wqd on 2017/3/9.
 */
public interface Pipeline {

    //设置基本阀门
    public void setBasic(Valve basic);

    //获取基本阀门
    public Valve getBasic();

    //添加阀门
    public void addValve(Valve valve);

    //删除阀门
    public void remove(Valve valve);

    //获得所有非基本阀门
    public Valve[] getValves();

    //代理
    public void invoke(Request request, Response response);
}
