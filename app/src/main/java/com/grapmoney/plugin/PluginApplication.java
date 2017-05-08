package com.grapmoney.plugin;

import android.app.Application;
import android.content.Intent;

import com.grapmoney.plugin.utils.LogUtils;

/**
 * Created by Administrator on 2017/5/8.
 */

public class PluginApplication extends Application {

    private final static String TAG = "PluginApplication";
    private final static String PLUGIN_SERVICE_ACTION = "com.android.guard.localService.ACTION";
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG + " onCreate()");
        startService(new Intent(PLUGIN_SERVICE_ACTION).setPackage(PluginApplication.this.getPackageName()));

    }
}
