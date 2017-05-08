package com.grapmoney.plugin.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2017/5/8.
 */

public class LogUtils {

    public final static String DEFAULT_TAG = "GRAP";
    public final static String TAG_FILE = Environment.getExternalStorageDirectory() + "/LogLevel.dat";
    public static boolean PRINT_LOG = false;

    public static boolean isExistLogFile(){
        if(!PRINT_LOG){
            PRINT_LOG = new File(TAG_FILE).exists();
            Log.i(DEFAULT_TAG ," PRINT_LOG = " + PRINT_LOG + " , TAG_FILE = " + TAG_FILE);
        }
        return PRINT_LOG;
    }

    public  static void i(String msg){
        if(isExistLogFile())
        {
            Log.i(DEFAULT_TAG , msg);
        }
    }

    public static  void i(String tag ,String msg)
    {
        if(isExistLogFile())
        {
            Log.i(tag , msg);
        }
    }

    public static void d(String msg)
    {
        if(isExistLogFile())
        {
            Log.d(DEFAULT_TAG , msg);
        }
    }

    public static void d(String tag , String msg)
    {
        if(isExistLogFile())
        {
            Log.d(tag , msg);
        }
    }

    public static void w(String msg)
    {
        if(isExistLogFile())
        {
            Log.w(DEFAULT_TAG , msg );
        }
    }

    public static void w(String tag , String msg)
    {
        if(isExistLogFile())
        {
            Log.w(tag, msg);
        }
    }

    public static void e(String msg)
    {
        if(isExistLogFile())
        {
            Log.e(DEFAULT_TAG , msg);
        }
    }

    public static void e(String tag , String msg)
    {
        if(isExistLogFile())
        {
            Log.e(tag , msg);
        }
    }

    public static void exception(Exception e)
    {
        if(isExistLogFile())
        {
            e.printStackTrace();
//            Log.e(DEFAULT_TAG , e.toString());
        }
    }

    public static void exception(String tag , Exception e)
    {
        if(isExistLogFile()){
            Log.e(tag , e.toString());
        }
    }

}
