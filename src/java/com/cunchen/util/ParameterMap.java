package com.cunchen.util;

import org.apache.tomcat.util.res.StringManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqd on 2017/1/4.
 */
public final class ParameterMap extends HashMap {

    public ParameterMap() {
        super();
    }

    public ParameterMap(Map m) {
        super(m);
    }

    public ParameterMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ParameterMap(int initialCapacity) {
        super(initialCapacity);
    }

    private boolean locked = false;

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocaked(boolean locaked) {
        this.locked = locaked;
    }

    private static final StringManager sm = StringManager.getManager("org.apache.catalina.util");

    public void clear() {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        super.clear();
    }

    public Object put(Object key, Object value) {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        return super.put(key, value);
    }

    public void putAll(Map map) {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        super.putAll(map);
    }

    public Object remove(Object key) {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        return super.remove(key);
    }
}
