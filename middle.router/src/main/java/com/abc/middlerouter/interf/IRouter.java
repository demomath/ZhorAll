package com.abc.middlerouter.interf;

import android.app.Activity;

/**
 * Created by wudi on 2018/4/25.
 */

public interface IRouter {

    void go(Activity context);

    void go(Activity context, RouterCallBack callBack);

}
