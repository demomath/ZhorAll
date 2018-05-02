package com.abc.middlerouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.abc.libcore.rxtools.view.RxToast;
import com.abc.middlerouter.interf.IRouter;
import com.abc.middlerouter.interf.RouterCallBack;

import java.util.ArrayList;

/**
 * Created by wudi on 2018/4/27.
 * 路由跳转实体类和控制类
 */
public class Router implements IRouter{

    private volatile static Router sIntance;
    private String mPath;
    private Uri mUri;
    private int mRequestCode;
    private Bundle mBundle;
    private int mFlag;
    private @AnimatorRes int mEnter = -1;
    private @AnimatorRes int mExit = -1;

    // TODO: 2018/4/27 未用到回调
    private RouterCallBack mCallBack;

    private Router() {}

    public static Router build(String path) {
        if (sIntance == null) {
            synchronized (Router.class) {
                if (sIntance == null) {
                    sIntance = new Router();
                    sIntance.mPath = path;
                }
            }
        }
        return sIntance;
    }

    public Router requestCode (int requestCode) {
        sIntance.mRequestCode = requestCode;
        return sIntance;
    }

    public Router with (Bundle bundle) {
        sIntance.mBundle = bundle;
        return sIntance;
    }

    public Router addFlag (int flag) {
        sIntance.mFlag = flag;
        return sIntance;
    }

    public Router anim (@AnimatorRes int enter,@AnimatorRes int exit) {
        sIntance.mEnter = enter;
        sIntance.mExit = exit;
        return sIntance;
    }

    public Router callBack (RouterCallBack callBack) {
        sIntance.mCallBack = callBack;
        return sIntance;
    }

    @Override
    public void go(@NonNull Activity context) {
        go(context,null);
    }

    @Override
    public void go(@NonNull Activity context, RouterCallBack callBack) {
        if (!pathIsCorrect(context, sIntance.mPath)) {
            reset();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, sIntance.mUri);
        intent.setFlags(sIntance.mFlag);
        context.startActivityForResult(intent, sIntance.mRequestCode, sIntance.mBundle);
        if (sIntance.mEnter == -1 || sIntance.mExit == -1) {
            reset();
            return;
        }
        context.overridePendingTransition(sIntance.mEnter, sIntance.mExit);
        reset();
    }

    /**
     * 重置为空，下次使用
     */
    private void reset() {
        sIntance = null;
    }

    /**
     * path是否正确
     * @param context
     * @param path
     * @return
     */
    private boolean pathIsCorrect(Context context,String path) {
        if (TextUtils.isEmpty(path)) {
            RxToast.error(context, "跳转未设置path!", Toast.LENGTH_SHORT, true).show();
            return false;
        }
        ArrayList<Uri> list = RouterManager.getInstance().getList();
        Uri aimUri = null;
        for (Uri uri : list) {
            if (uri != null && uri.getPath().equals(sIntance.mPath)) {
                aimUri = uri;
                break;
            }
        }
        if (aimUri == null) {
            RxToast.error(context, "目标path未找到!", Toast.LENGTH_SHORT, true).show();
            return false;
        }
        sIntance.mUri = aimUri;
        return true;
    }
}
