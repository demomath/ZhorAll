package com.abc.libimg.loader;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.MemoryCategory;

import com.abc.libimg.config.SingleConfig;
import com.abc.libimg.utils.DownLoadImageService;

/**
 * Created by wudi on 2017/4/10.
 */

public interface ILoader {

    void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD);

    void request(SingleConfig config);

    void pause();

    void resume();

    void clearDiskCache();

    void clearMomoryCache(View view);

    void clearMomory();

    boolean  isCached(String url);

    void trimMemory(int level);

    void clearAllMemoryCaches();

    void saveImageIntoGallery(DownLoadImageService downLoadImageService);
}
