package com.cunchen.core;

import com.cunchen.*;
import com.cunchen.util.LifecycleSupport;

/**
 * SimpleContext
 * Created by qidong on 2017/8/6.
 */
public class SimpleContext extends ContainerBase implements Context, Lifecycle {

    private LifecycleSupport lifecycleSupport = new LifecycleSupport(this);

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.addLiffecycleListener(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycleSupport.findLifecycleListeners();
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.removeLifecycleListener(listener);
    }

    @Override
    public void start() throws LifecycleException {

        lifecycleSupport.fireLifecycleEvent(Lifecycle.BEFORE_START_EVENT, null);


        lifecycleSupport.fireLifecycleEvent(Lifecycle.START_EVENT, null);
        Container[] lifeChildren = findChildren();
        for (Container child :
                lifeChildren) {
            ((Lifecycle) child).start();
        }

        lifecycleSupport.fireLifecycleEvent(Lifecycle.AFTER_START_EVENT, null);

    }

    @Override
    public void stop() throws LifecycleException {
        lifecycleSupport.fireLifecycleEvent(Lifecycle.BEFORE_START_EVENT, null);

        lifecycleSupport.fireLifecycleEvent(Lifecycle.STOP_EVENT, null);
        Container[] lifeChildren = findChildren();
        for (Container child :
                lifeChildren) {
            ((Lifecycle) child).stop();
        }

        lifecycleSupport.fireLifecycleEvent(Lifecycle.AFTER_STOP_EVENT, null);
    }

}
