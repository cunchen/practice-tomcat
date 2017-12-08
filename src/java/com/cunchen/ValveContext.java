package com.cunchen;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * ValveContext
 * Created by wqd on 2017/3/9.
 */
public interface ValveContext {

    public String getInfo();


    //代理方法
    public void invokeNext(Request request, Response response) throws IOException, ServletException;
}
