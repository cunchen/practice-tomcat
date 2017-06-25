package com.cunchen.util;

import org.apache.catalina.util.StringManager;

import java.util.HashMap;
import java.util.Map;

/**
 * ParameterMap
 * Created by wqd on 2017/1/4.
 */
public final class ParameterMap<K,V> extends HashMap<K,V> {

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

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    private static final StringManager sm = StringManager.getManager("org.apache.catalina.util");

    public void clear() {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        super.clear();
    }

    public V put(K key, V value) {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        return super.put(key, value);
    }

    public void putAll(Map map) {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        super.putAll(map);
    }

    public V remove(Object key) {
        if(locked)
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        return super.remove(key);
    }
}
