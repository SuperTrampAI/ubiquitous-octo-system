package com.github.supertrampai.util;
 

public class Et {

    public static void callEx(String msg) {
        throw new CusException(msg);
    }
    public static void callEx(Integer code,String msg) {
        throw new CusException(code, msg);
    }
    public static CusException getInstance(String msg) {
        return new CusException(msg);
    }


   
}
