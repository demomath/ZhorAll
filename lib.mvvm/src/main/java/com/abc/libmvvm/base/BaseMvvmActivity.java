package com.abc.libmvvm.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.abc.libcore.activity.BaseAppActivity;
import com.abc.libcore.rxtools.RxLogTool;

/**
 * Created by wudi on 2018/4/28.
 */
public abstract class BaseMvvmActivity<VM extends BaseMvvmVM<? extends BaseMvvmActivity,?,? extends ViewDataBinding>,B extends ViewDataBinding>
        extends BaseAppActivity {

    public VM mViewModel;
    public B mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setLayoutToView();
        bindingDefModel();
        mViewModel = createVM();
        if (mViewModel==null) {
            RxLogTool.e("当前页面（"+getLocalClassName()+"）未绑定ViewModel！");
        } else {
            bindingVM();
        }
        initView();

        initData();
    }

    public abstract B setLayoutToView();

    public abstract void bindingDefModel();

    public abstract VM createVM();

    protected abstract void bindingVM();

    protected abstract void initView();

    protected abstract void initData();
}
