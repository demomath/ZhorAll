package com.abc.libmvvm.base;

import com.abc.libcore.rxtools.RxLogTool;

import rx.Subscriber;

/**
 * Created by wudi on 2018/5/2.
 *
 * 可实现升级。。。等拦截
 */

public abstract class BaseMvvmSubscriber<T> extends Subscriber<BaseMvvmModel<T>> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onError(Throwable e) {
        RxLogTool.e(e.getMessage());
    }

    @Override
    public void onNext(BaseMvvmModel<T> t) {
        if (t == null) {
            onError(new NullPointerException("网络请求返回值解析为空"));
            return;
        }
        if (!checkCode200(t)) {
            return;
        }
        if (!checkData(t)) {
            return;
        }
        onSuccess(t.getData());
    }

    @Override
    public void onCompleted() {

    }

    /**
     * 成功，确保返回值正确性
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 自定义错误
     * @param code
     * @param message
     */
    public abstract void onFail(String code,String message);

    /**
     * 服务器返回码检验
     * fixme
     *
     * @param baseMvvmModel
     * @return
     */
    private boolean checkCode200(BaseMvvmModel baseMvvmModel) {
        return true;
    }


    /**
     * 服务器返回数据校验
     * fixme
     *
     * @param t
     * @return
     */
    private boolean checkData(BaseMvvmModel<T> t) {
        return true;
    }
}
