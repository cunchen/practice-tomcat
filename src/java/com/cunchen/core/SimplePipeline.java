package com.cunchen.core;

import com.cunchen.Pipeline;
import com.cunchen.Valve;
import com.cunchen.ValveContext;
import org.apache.catalina.Container;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

/**
 * SimplePipeline
 * Created by wqd on 2017/3/9.
 */
public class SimplePipeline implements Pipeline {

    private Container container;

    private Valve basicValve;
    private Valve[] valves;

    private int valveSize = 0;

    public SimplePipeline(Container container) {
        this.container = container;
    }

    @Override
    public org.apache.catalina.Valve getBasic() {
        return null;
    }

    @Override
    public void setBasic(Valve valve) {
        basicValve = valve;
    }

    @Override
    public void addValve(Valve valve) {
        valves[valveSize++] = valve;
    }

    @Override
    public org.apache.catalina.Valve[] getValves() {
        return new org.apache.catalina.Valve[0];
    }

    @Override
    public void removeValve(Valve valve) {

    }

    @Override
    public org.apache.catalina.Valve getFirst() {
        return null;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public Container getContainer() {
        return null;
    }

    @Override
    public void setContainer(Container container) {

    }

    public void invoke(Request request, Response response) {


    }

    protected class StandardPipelineValveContext implements ValveContext {

        protected int stage = 0;

        public String getInfo() {
            return null;

        }

        public void invokeNext(Request request, Response response) {
            int subscript = stage;
            stage = stage + 1;

            if (subscript < valves.length) {
                valves[subscript].invoke(request, response, this);
            }
        }

    }
}
