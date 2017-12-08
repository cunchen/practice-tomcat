package com.cunchen;

/**
 * LifecycleException
 * Created by qidong on 2017/9/4.
 */
public class LifecycleException extends Throwable {

    public LifecycleException() {
    }

    public LifecycleException(String message) {
        super(message);
    }

    public LifecycleException(String message, Throwable cause) {
        super(message, cause);
    }

    public LifecycleException(Throwable cause) {
        super(cause);
    }
}
