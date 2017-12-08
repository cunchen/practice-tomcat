package com.cunchen.core;


import com.cunchen.*;

import javax.servlet.ServletException;
import java.io.IOException;

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
    public Valve getBasic() {
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
    public void remove(Valve valve) {

    }

    @Override
    public Valve[] getValves() {
        return new Valve[0];
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {

    }


    protected class StandardPipelineValveContext implements ValveContext {

        protected int stage = 0;

        public String getInfo() {
            return null;

        }

        @Override
        public void invokeNext(Request request, Response response) throws IOException, ServletException {
            int subscript = stage;
            stage = stage + 1;

            if (subscript < valves.length) {
                valves[subscript].invoke(request, response, this);
            }
        }
    }
}
