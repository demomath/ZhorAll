package com.abc.libmvvm.base;

import android.databinding.ViewDataBinding;

import com.abc.libmvvm.interf.ViewModel;

import rx.Subscriber;

/**
 * Created by wudi on 2018/4/28.
 */

public abstract class BaseMvvmVM<V extends BaseMvvmActivity,T,B extends ViewDataBinding> extends BaseMvvmSubscriber<T> implements ViewModel{

    public V mView;
    public T mModel;
    public B mBinding;

    public BaseMvvmVM(V view, B binding) {
        mView = view;
        mBinding = binding;
    }
}
