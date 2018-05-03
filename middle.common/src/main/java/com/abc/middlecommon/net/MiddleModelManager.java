package com.abc.middlecommon.net;

import com.abc.libnet.RetrofitManager;

/**
 * Created by wudi on 2018/5/2.
 */

public class MiddleModelManager<T> {

    public T mApi;

    protected MiddleModelManager(Class<T> clazz) {
        mApi = RetrofitManager.create(clazz);
    }

}
