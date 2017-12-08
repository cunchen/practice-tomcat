package com.cunchen.loader;

import com.cunchen.Container;
import com.cunchen.Loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLStreamHandler;

/**
 * SimpleLoader
 * Created by qidong on 2017/9/7.
 */
public class SimpleLoader implements Loader {

    private static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    private ClassLoader loader;
    private Container container;

    public SimpleLoader() {
        this(SimpleLoader.class.getClassLoader());
    }

    public SimpleLoader(ClassLoader loader) {
        this.loader = loader;

        URL[] urls = new URL[1];
        URLStreamHandler streamHandler = null;
        File classPath = new File(WEB_ROOT);
        try {
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return loader;
    }

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {

        this.container = container;
    }
}
