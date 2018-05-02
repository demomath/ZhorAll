package com.abc.zhor;


import android.app.Application;
import android.net.Uri;


import com.abc.libcore.rxtools.RxLogTool;
import com.abc.middlerouter.RouterManager;

import java.util.List;

/**
 * Created by wudi on 2018/4/23.
 */

public class ZhorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化路由哈希集合
        List<Uri> activityUriList = RouterManager.getInstance().init(this);
        for (Uri uri : activityUriList) {
            RxLogTool.e(uri.toString());
        }


    }
}
