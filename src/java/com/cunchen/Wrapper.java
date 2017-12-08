package com.cunchen;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

/**
 * Wrapper
 * Created by cunchen on 2017/3/9.
 */
public interface Wrapper extends Container {


    /**
     * allocate servlet instance
     * @return Servlet
     */
    Servlet allocate() throws ServletException;
}
