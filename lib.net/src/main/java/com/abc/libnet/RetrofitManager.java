package com.abc.libnet;

import android.content.Context;


import com.abc.libcore.rxtools.RxLogTool;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wudi on 17-5-2.
 *
 * retrofit 管理者
 */
public class RetrofitManager {

    private static volatile Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    // 公共参数
    private static Map<String, Subscription> mRequestMap = new ConcurrentHashMap<>();

    public static RetrofitBuilder init(Context context, String baseUrl) {
        return new RetrofitBuilder(context,baseUrl);
    }

    static void build(RetrofitBuilder retrofitBuilder) {
        if (mRetrofit == null) {
            synchronized (Object.class) {
                if (mRetrofit == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(new Cache(new File(retrofitBuilder.getContext().getExternalCacheDir(), "zhor_cache"), 1024 * 1024 * 100))
                            .connectTimeout(retrofitBuilder.getConnectTimeoutSeconds(), TimeUnit.SECONDS)
                            .readTimeout(retrofitBuilder.getConnectTimeoutSeconds(), TimeUnit.SECONDS)
                            .writeTimeout(retrofitBuilder.getConnectTimeoutSeconds(), TimeUnit.SECONDS)
                            .addInterceptor(retrofitBuilder.getInterceptorParams())
                            .addInterceptor(retrofitBuilder.getInterceptorCommon())
                            .addInterceptor(retrofitBuilder.getInterceptorLogging())
                            .cookieJar(retrofitBuilder.getManagerCookie())
                            .build();

                    mRetrofit = new Retrofit.Builder()
                            .client(mOkHttpClient)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(retrofitBuilder.getBaseUrl())
                            .build();

                }
            }
        }
    }

    /**
     * 创建api
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> tClass) {
        if (mRetrofit == null) {
            RxLogTool.e("Retrofit需要先build");
            return null;
        }
        return mRetrofit.create(tClass);
    }

}
