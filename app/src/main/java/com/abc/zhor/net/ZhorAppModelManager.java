package com.abc.zhor.net;

import com.abc.libmvvm.base.BaseMvvmSubscriber;
import com.abc.middlecommon.net.MiddleModelManager;
import com.abc.zhor.model.ZhorAppModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wudi on 2018/5/2.
 */

public class ZhorAppModelManager extends MiddleModelManager<ZhorAppApi>{

    private static volatile ZhorAppModelManager sZhorAppModelManager;

    protected ZhorAppModelManager() {
        super(ZhorAppApi.class);
    }


    public static ZhorAppModelManager getInstance () {
        if (sZhorAppModelManager == null) {
            synchronized (ZhorAppModelManager.class) {
                if (sZhorAppModelManager == null) {
                    sZhorAppModelManager = new ZhorAppModelManager();
                }
            }
        }
        return sZhorAppModelManager;
    }

    public void test(String param, BaseMvvmSubscriber<ZhorAppModel> subscriber) {
        sZhorAppModelManager.mApi.test(param)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
