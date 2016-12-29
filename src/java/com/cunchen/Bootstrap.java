package com.cunchen;

import com.cunchen.connector.HttpConnector;

/**
 * 启动入口
 * Created by wqd on 2016/12/28.
 */
public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
