package com.cunchen.server.processor;

import com.cunchen.server.io.HttpRequest;
import com.cunchen.server.io.HttpResponse;
import com.cunchen.server.io.Request;
import com.cunchen.server.io.Response;

/**
 * Created by wqd on 2016/12/26.
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {

        response.sendStaticResource();
    }
}
