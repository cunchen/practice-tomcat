package com.cunchen;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

/**
 * 阀门接口
 * Created by wqd on 2017/3/9.
 */
public interface Valve {

    public String getInfo() ;

    public void invoke(Request request, Response response, ValveContext context);

}
