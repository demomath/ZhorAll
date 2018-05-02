package com.abc.middlecommon;

import android.app.Application;
import android.content.Context;


/**
 * Created by wudi on 2018/4/24.
 */

public class CommonApplication extends Application {
    private static CommonApplication sInstance;
    public static Context context;
    public static CommonApplication getIns() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = this.getApplicationContext();
    }

    private boolean isAppDebug(Context context) {
        return false;
    }
}
