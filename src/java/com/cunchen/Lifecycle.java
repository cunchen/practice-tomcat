package com.cunchen;

/**
 * Lifecycle
 * Created by qidong on 2017/9/4.
 */
public interface Lifecycle {


    String START_EVENT = "start";

    String BEFORE_START_EVENT = "before_start";

    String AFTER_START_EVENT = "after_start";

    String STOP_EVENT = "stop";

    String BEFORE_STOP_EVENT  = "before_stop";

    String AFTER_STOP_EVENT = "after_stop";

    void addLifecycleListener(LifecycleListener listener);
    LifecycleListener[] findLifecycleListeners();
    void removeLifecycleListener(LifecycleListener listener);
    void start() throws LifecycleException;
    void stop() throws LifecycleException;
}
