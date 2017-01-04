package com.cunchen.test;

import java.util.Properties;
import java.util.Set;

/**
 * Created by wqd on 2016/12/27.
 */
public class SystemProperties {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Set<String> names = properties.stringPropertyNames();
        for (String name :
                names) {
            System.out.println(name + "\t:\t" + properties.getProperty(name));
        }
        System.out.println();
    }
}
