package com.atguigu.shoppingmall0224.shoppingcart.utils;

/**
 * Created by Administrator on 2017/6/14.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {
    /**
     * 得到String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }


    /**
     * 保持文本数据
     * @param context
     * @param key
     * @param value
     */
    public static void   putString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}

