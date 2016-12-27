package com.cunchen.server.processor;

import com.cunchen.server.io.Request;
import com.cunchen.server.io.Response;

/**
 * Created by wqd on 2016/12/26.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {

        response.sendStaticResource();
    }
}
