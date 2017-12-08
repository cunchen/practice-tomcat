package com.cunchen.util;

import com.cunchen.Lifecycle;
import com.cunchen.LifecycleEvent;
import com.cunchen.LifecycleListener;

/**
 * LifecycleSupport
 * Created by qidong on 2017/9/4.
 */
public final class LifecycleSupport {

    private  Lifecycle lifecycle;

    private LifecycleListener listeners[] = new LifecycleListener[0];

    public LifecycleSupport(Lifecycle lifecycle) {
        super();
        this.lifecycle = lifecycle;
    }

    public void addLiffecycleListener(LifecycleListener listener) {
        synchronized (listeners) {

            LifecycleListener results[] = new LifecycleListener[listeners.length + 1];
            for (int i = 0; i < listeners.length; i++) {
                results[i] = listeners[i];
            }
            results[listeners.length] = listener;
            listeners = results;
        }
    }

    public LifecycleListener[] findLifecycleListeners() {
        return listeners;
    }

    public void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
        LifecycleListener interested[] = null;

        synchronized (listeners) {
            interested = (LifecycleListener[]) listeners.clone();
        }

        for (int i = 0; i < interested.length; i++) {
            interested[i].lifecycleEvent(event);
        }

    }

    public void removeLifecycleListener(LifecycleListener listener) {
        synchronized (listeners) {
            int n = -1;
            for (int i = 0; i < listeners.length; i++) {
                if(listeners[i] == listener) {
                    n = i;
                    break;
                }
            }
            if(n < 0)
                return;

            LifecycleListener results[] = new LifecycleListener[listeners.length - 1];

            int j = 0;
            for (int i = 0; i < listeners.length; i++) {
                if(i != n) {
                    results[j++ ] = listeners[i];
                }
            }
            listeners = results;
        }
    }
}
