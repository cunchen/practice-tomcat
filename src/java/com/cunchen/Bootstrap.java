package com.cunchen;

import com.cunchen.connector.HttpConnector;
import com.cunchen.core.ClientIPLoggerValve;
import com.cunchen.core.SimpleContext;
import com.cunchen.core.SimpleWrapper;
import com.cunchen.core.SimpleWrapperValve;
import com.cunchen.loader.SimpleLoader;

/**
 * 启动入口
 * Created by wqd on 2016/12/28.
 */
public class Bootstrap {

    /**
     * 启动
     * @param args
     */
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();

        Wrapper wrapper = new SimpleWrapper();
        Context context = new SimpleContext();
        Valve valve1 = new ClientIPLoggerValve();
        Valve valve2 = new SimpleWrapperValve();

        ((Pipeline)context).addValve(valve1);
        ((Pipeline)context).addValve(valve2);
        ((Pipeline)context).setBasic((Valve) wrapper);

        context.addChild(wrapper);

        Loader loader = new SimpleLoader();
        loader.setContainer(context);

        try {
            httpConnector.start();

        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
