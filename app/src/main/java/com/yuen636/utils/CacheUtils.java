package com.yuen636.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {
    /**
     * 读取缓存
     * @param context ：上下文
     * @param key: 请求的url
     * @return
     */
    public static String readCache(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("cacheUtils", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
    /**
     * 保存缓存
     * @param context
     * @param key
     * @param result：json结果
     */
    public static void saveCache(Context context, String key, String result) {
        SharedPreferences sp = context.getSharedPreferences("cacheUtils", Context.MODE_PRIVATE);
        sp.edit().putString(key, result).apply();
    }

}
