package com.cunchen;

import java.util.EventObject;

/**
 * LifecycleEvent
 * Created by qidong on 2017/9/4.
 */
public final class LifecycleEvent extends EventObject {

    private final Lifecycle lifecycle;
    private final String type;
    private final Object data;

    public LifecycleEvent(Object source, Lifecycle lifecycle, String type) {
        this(lifecycle, type, null);
    }

    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {
        super(lifecycle);
        this.lifecycle = lifecycle;
        this.type = type;
        this.data = data;
    }


    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
