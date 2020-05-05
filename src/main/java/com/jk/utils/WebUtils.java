package com.jk.utils;


public class WebUtils {

    public static int parseInt(String strInt,int defaultValue){
        if (strInt == null){//前台客户端传参一定会是null,null转integer会报错
            return defaultValue;
        }

        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            //e.printStackTrace();

        }

        return defaultValue;
    }

}
