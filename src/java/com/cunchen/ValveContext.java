package com.cunchen;

import java.io.IOException;

/**
 * ValveContext
 * Created by wqd on 2017/3/9.
 */
public interface ValveContext {

    public void invokeNext(Request request, Response response) throws IOException;
}
