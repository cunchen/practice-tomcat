package com.cunchen;

import java.io.File;

/**
 * 公共静态常量
 * Created by wqd on 2016/12/26.
 */
public class Constants {
    public final static String WEB_ROOT = Constants.class.getClassLoader().getResource("").getFile();
}
