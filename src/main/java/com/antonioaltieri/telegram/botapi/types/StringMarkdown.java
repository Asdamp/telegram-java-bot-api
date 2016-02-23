package com.antonioaltieri.telegram.botapi.types;

/**
 * Created by Antonio on 13/12/2015.
 */
public class StringMarkdown{
    public static String bold(String str){
        return "*"+str+"*";
    }
    public static String italic(String str){
        return "_"+str+"_";
    }
    public static String inlineLink(String text,String URL){
        return "["+text+"]"+"("+URL+")";
    }

    public static String oneLineCode(String code){
        return "`"+code+"`";
    }
    public static String multiLineCode(String code){
        return "```"+code+"```";
    }
}
