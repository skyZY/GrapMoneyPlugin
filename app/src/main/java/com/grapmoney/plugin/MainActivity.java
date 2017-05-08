package com.grapmoney.plugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.grapmoney.plugin.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.d(TAG + " onCreate()");
    }
}
