package com.cunchen;

import org.apache.catalina.Container;

/**
 * Pipeline
 * Created by wqd on 2017/3/9.
 */
public interface Pipeline {
    org.apache.catalina.Valve getBasic();

    void setBasic(Valve var1);

    void addValve(Valve var1);

    org.apache.catalina.Valve[] getValves();

    void removeValve(Valve var1);

    org.apache.catalina.Valve getFirst();

    boolean isAsyncSupported();

    Container getContainer();

    void setContainer(Container var1);
}
