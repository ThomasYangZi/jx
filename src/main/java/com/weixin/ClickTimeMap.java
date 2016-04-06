package com.weixin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 2016/2/25.
 * 签到点击时间
 */
public class ClickTimeMap {
    private static Map<String,Long> cachemap = new HashMap<String,Long>();

    public static void addClickTimeMap(String key, long clicktime){
        cachemap.put(key, clicktime);
    }

    public static void deleteClickTimeMap(String key){
        cachemap.remove(key);
    }

    public static long getClickTime(String key){
        long clicktime = 0l;
        if (cachemap.get(key)!=null) {
            clicktime = cachemap.get(key);
        }
        return clicktime;
    }
}
