package com.cunchen;

import java.io.File;

/**
 * 公共静态常量
 * Created by wqd on 2016/12/26.
 */
public class Constants {
    public final static String WEB_ROOT = Constants.class.getClassLoader().getResource("").getFile();

    //punctuation ?
    public final static String PUNCATUATION_QUESTION = "?";

    //Slash
    public final static String PUNCATUATION_SLASH = "/";

    //Backslash
    public final static String PUNCATUATION_BACkSLASH = "\\";

    //semicolon
    public final static String PUNCATUATION_SEMICOLON = ";";

    //black
    public final static String PUNCATUATION_BLACK = " ";

    //punctuation &
    public final static String PUNCATUATION_AND = "&";

    //punctuation :
    public final static String PUNCATUATION_COLON = ":";
}
