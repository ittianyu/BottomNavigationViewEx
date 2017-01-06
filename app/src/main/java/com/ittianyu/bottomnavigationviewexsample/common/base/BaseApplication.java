package com.ittianyu.bottomnavigationviewexsample.common.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by yu on 2016/11/24.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        initLogger();

        initLeakCanary();
    }

    /**
     * use LeakCanary to check mey leak
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * init logger
     */
//    private void initLogger() {
//        if ((0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE)))
//            Logger.init(); // for debug, print all log
//        else
//            Logger.init().logLevel(LogLevel.NONE); // for release, remove all log
//    }
}
