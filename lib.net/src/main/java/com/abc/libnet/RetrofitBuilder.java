package com.abc.libnet;

import android.content.Context;

import com.abc.libnet.defconfig.DefInterceptorCommon;
import com.abc.libnet.defconfig.DefInterceptorLogging;
import com.abc.libnet.defconfig.DefInterceptorParams;
import com.abc.libnet.defconfig.DefManagerCookie;

import java.util.Map;

import okhttp3.CookieJar;
import okhttp3.Interceptor;

/**
 * Created by wudi on 17-5-2.
 *
 * retrofit 构造者
 */
public class RetrofitBuilder {


    private Context mContext;

    private String mBaseUrl;

    private long mConnectTimeoutSeconds;

    private Interceptor mInterceptorCommon;

    private Interceptor mInterceptorLogging;

    private Interceptor mInterceptorParams;

    private CookieJar mManagerCookie;

    private Map<String,String> mParamsMap;

    /**
     * 构造方法
     * @param context 上下文对象
     */
    public RetrofitBuilder(Context context , String baseUrl) {

        //[2]得到Application的上下文
        this.mContext = context.getApplicationContext();
        this.mBaseUrl = baseUrl;
    }

    public void build () {
        RetrofitManager.build(this);
    }

    public Context getContext() {
        return mContext;
    }

    public RetrofitBuilder setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    public Interceptor getInterceptorCommon() {
        if ( mInterceptorCommon == null) {
            mInterceptorCommon = new DefInterceptorCommon(mContext);
        }
        return mInterceptorCommon;
    }

    public RetrofitBuilder setInterceptorCommon(DefInterceptorCommon mDefInterceptorCommon) {
        this.mInterceptorCommon = mDefInterceptorCommon;
        return  this;
    }

    public Interceptor getInterceptorLogging() {
        if (mInterceptorLogging == null) {
            mInterceptorLogging = new DefInterceptorLogging();
        }
        return mInterceptorLogging;
    }

    public RetrofitBuilder setInterceptorLogging(DefInterceptorLogging mDefInterceptorLogging) {
        this.mInterceptorLogging = mDefInterceptorLogging;
        return this;
    }

    public Interceptor getInterceptorParams() {
        if (mInterceptorParams == null) {
            if (mParamsMap != null ) {
                mInterceptorParams = new DefInterceptorParams(mParamsMap);
            } else {
                mInterceptorParams = new DefInterceptorParams();
            }
        }
        return mInterceptorParams;
    }

    public RetrofitBuilder setInterceptorParams(DefInterceptorParams mDefInterceptorParams) {
        this.mInterceptorParams = mDefInterceptorParams;
        return this;
    }

    public CookieJar getManagerCookie() {
        if (mManagerCookie == null) {
            mManagerCookie = new DefManagerCookie();
        }
        return mManagerCookie;
    }

    public RetrofitBuilder setManagerCookie(DefManagerCookie mDefManagerCookie) {
        this.mManagerCookie = mDefManagerCookie;
        return this;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public RetrofitBuilder setmBaseUrl(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
        return this;
    }

    public Map<String, String> getmParamsMap() {
        return mParamsMap;
    }

    public RetrofitBuilder setParamsMap(Map<String, String> paramsMap) {
        this.mParamsMap = paramsMap;
        return this;
    }

    public long getConnectTimeoutSeconds() {
        if (mConnectTimeoutSeconds == 0) {
            // FIXME: 过期时间自己定义
            mConnectTimeoutSeconds = 300;
        }
        return mConnectTimeoutSeconds;
    }

    /**
     * 设置超时时间
     * @param connectTimeoutSeconds 单位是秒
     * @return
     */
    public RetrofitBuilder setConnectTimeoutSeconds(long connectTimeoutSeconds) {
        this.mConnectTimeoutSeconds = connectTimeoutSeconds;
        return this;
    }
}
