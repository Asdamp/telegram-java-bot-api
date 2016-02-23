package com.antonioaltieri.telegram.botapi.types;

/**
 * Created by Antonio on 21/02/2016.
 */
public class StringHTML {
    public static String bold(String str){
        return "<b>"+str+"</b>";
    }
    public static String italic(String str){
        return "<i>"+str+"</i>";
    }
    public static String inlineLink(String text,String URL){
        return "<a href=\""+URL+"\">"+URL+"</a>";
    }

    public static String oneLineCode(String code){
        return "<code>"+code+"</code>";
    }
    public static String multiLineCode(String code){
        return "<pre>"+code+"</pre>";
    }
}
