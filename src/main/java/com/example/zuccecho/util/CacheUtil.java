package com.example.zuccecho.util;

public class CacheUtil {

    public static final String FEEDBACK_KEY_PREFIX = "Feedback";
    public static final String SHOULD_FILL_FORM_PREFIX ="Should_fill";
    public static final String UNFINISHED_FORM = "Unfinished_form";
    public static final String FINISHED_FORM = "Finished_form";
    public static final String MESSAGE = "Message";

    public static String generateCacheKey(String ...values){
        StringBuffer res = new StringBuffer("zuccEcho");
        for(String it:values){
            res.append(":").append(it);
        }
        return res.toString();
    }

    public static String cacheKey(String KEY_PREFIX,Integer keyId){
        return CacheUtil.generateCacheKey(KEY_PREFIX,keyId.toString());
    }
}
