package com.abc.libnet.defconfig;


import android.content.Context;

import com.abc.libcore.rxtools.RxNetTool;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;


/**
 * Created by wudi on 16-10-20.
 * 通用拦截
 *
 * <p>用来拦截请求，设置通用参数</p>
 */
public class DefInterceptorCommon implements Interceptor {

    private final Context mContext;
    private final File mCacheFile;
    private final Cache mCache;


    public DefInterceptorCommon (Context context) {
        this.mContext = context;
        mCacheFile = new File( context.getExternalCacheDir(), "zhor_cache");
        mCache = new Cache(mCacheFile, 1024 * 1024 * 50);
    }
    
    /**
     * 这里是设置缓存的
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        //【1】 Request
        Request.Builder request = chain.request().newBuilder();


        //无网络链接
        if (RxNetTool.getNetWorkType(mContext) == -1) {
            request.cacheControl(CacheControl.FORCE_CACHE);
        //联网
        } else {
            request.cacheControl(CacheControl.FORCE_NETWORK);
        }
        request.addHeader("User-Agent","Android");

        //【2】 Response
        Response.Builder response = chain.proceed(request.build()).newBuilder();

        if (RxNetTool.getNetWorkType(mContext) != -1) {
            // 有网络时 设置缓存超时时间0个小时
            response.removeHeader("Authorization")
                    .header("Cache-Control", "public, only-if-cached, max-age=" + 0)
                    .build();
        } else {
            // 无网络时，设置超时为4周
            response.removeHeader("Authorization")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 28)
                    .build();
        }
        return response.build();


/*
        Request originalRequest = chain.request();
        Request.Builder newBuilder = originalRequest.newBuilder();
        Request compressedRequest;

        if (!NetWorkUtils.isNetConnect(YHRetrofitManager.getInstance().mContext)) {
            newBuilder.cacheControl(CacheControl.FORCE_CACHE);//从缓存中读取
        } else {
            newBuilder.cacheControl(CacheControl.FORCE_NETWORK);
        }

        newBuilder.header("User-Agent", "Android");

        compressedRequest = newBuilder.build();

        Response response = chain.proceed(compressedRequest);

        if (NetWorkUtils.isNetConnect(YHRetrofitManager.getInstance().mContext)) {
            int maxAge = 60 * 60;  // 有网络时 设置缓存超时时间一小时
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .header("Cache-Control", "public,only-if-cached, max-age=" + maxAge)//设置缓存超时时间
                    .build();
        } else {
            int maxStale = 60 ; // 无网络时，设置超时为一分钟
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    //设置缓存策略，及超时策略
                    .build();
        }

        return response;*/
    }

    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                return -1; // 无法知道压缩后的数据大小
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
