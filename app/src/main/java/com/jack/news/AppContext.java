package com.jack.news;

import android.app.Application;
import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.widget.PopupWindow;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class AppContext extends Application {
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;

    @Override
    public void onCreate() {
        super.onCreate();
        initScreenSize();
        Fresco.initialize(this);
        initCrash();
    }

    /**
     * 初始化腾讯crash
     */
    private void initCrash(){
        PackageManager packmanager = getPackageManager();
        try {
            PackageInfo info = packmanager.getPackageInfo(getPackageName(), 0);
            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
            strategy.setAppVersion(info.versionName); //App的版本
            strategy.setAppPackageName(info.packageName); //App的包名
            CrashReport.initCrashReport(getApplicationContext(), "900033042", false);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

}
