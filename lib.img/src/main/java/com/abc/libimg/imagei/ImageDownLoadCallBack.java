package com.abc.libimg.imagei;

import android.graphics.Bitmap;

/**
 * Created by wudi on 2017/5/2.
 */

public interface ImageDownLoadCallBack {

    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
