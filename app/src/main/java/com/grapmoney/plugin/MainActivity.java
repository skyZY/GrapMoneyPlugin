package com.grapmoney.plugin;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.grapmoney.plugin.grap.RabMoneyService;
import com.grapmoney.plugin.utils.LogUtils;

public class MainActivity extends Activity implements View.OnClickListener{
    private final static String TAG = MainActivity.class.getName();
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        LogUtils.d(TAG + " onCreate()");

        findViewById(R.id.btn_check).setOnClickListener(this);
        findViewById(R.id.btn_send_notify).setOnClickListener(this);

        startService(new Intent(mContext , RabMoneyService.class).setPackage(mContext.getPackageName()));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btn_check :
                if (! isAccessibilitySettingsOn(getApplicationContext())) {
                    startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                }
                break;
            case R.id.btn_send_notify :
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);//NotificationManager实例对通知进行管理
                Notification notification=new Notification(R.mipmap.ic_launcher,"[微信红包]",System.currentTimeMillis());//创建Notification对象
                notification.setLatestEventInfo(this, "通知标题", "[微信红包]",null);
                notification.ledARGB= Color.GREEN;//控制通知的led灯颜色
                notification.ledOnMS=1000;//通知灯的显示时间
                notification.ledOffMS=1000;
                notification.flags=Notification.FLAG_SHOW_LIGHTS;
                manager.notify(1,notification);//调用NotificationManager的notify方法使通知显示

                break;

        }
    }

    // To check if service is enabled
    private boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        final String service = "com.test.package.name/com.test.package.name.YOURAccessibilityService";
        boolean accessibilityFound = false;
        try {
            accessibilityEnabled = Settings.Secure.getInt( context.getApplicationContext().getContentResolver(),android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: " + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.v(TAG, "***ACCESSIBILIY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString( context.getApplicationContext().getContentResolver(),Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;
                splitter.setString(settingValue);
                while (splitter.hasNext()) {
                    String accessabilityService = splitter.next();

                    Log.v(TAG, "-------------- > accessabilityService :: " + accessabilityService);
                    if (accessabilityService.equalsIgnoreCase(service)) {
                        Log.v(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Log.v(TAG, "***ACCESSIBILIY IS DISABLED***");
        }

        return accessibilityFound;
    }
}
