package com.cunchen;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.Request;

/**
 * ValveContext
 * Created by wqd on 2017/3/9.
 */
public interface ValveContext {

    public void invokeNext(Request request, Response response);
}
