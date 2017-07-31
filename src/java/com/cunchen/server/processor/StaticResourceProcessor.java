package com.cunchen.server.processor;

import com.cunchen.connector.HttpRequest;
import com.cunchen.connector.HttpResponse;

/**
 * Created by wqd on 2016/12/26.
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {

        response.sendStaticResource();
    }
}
