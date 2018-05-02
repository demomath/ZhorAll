package com.abc.middlecommon.mvvmbase;

import android.databinding.ViewDataBinding;

/**
 * Created by wudi on 2018/4/28.
 */

public abstract class BaseMvvmVM<V extends BaseMvvmActivity,M extends BaseMvvmModel,B extends ViewDataBinding> {

    public V mView;
    public M mModel;
    public B mBinding;

    public BaseMvvmVM(V view, M model, B binding) {
        mView = view;
        mModel = model;
        mBinding = binding;
    }


}
