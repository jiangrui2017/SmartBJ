package com.joy835368394.smartbj.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class SpTools {

    public static void setBoolean(Context context,String key, Boolean value){
        SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILL,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();

    }
    public static Boolean getBoolean(Context context,String key,Boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILL,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
}
