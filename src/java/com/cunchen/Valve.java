package com.cunchen;

import java.io.IOException;

/**
 * 阀门接口
 * Created by wqd on 2017/3/9.
 */
public interface Valve {

    public String getInfo() ;

    public void invoke(Request request, Response response, ValveContext context) throws IOException;

}
