package com.abc.middlecommon.mvvmbase;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wudi on 2018/4/28.
 */
public abstract class BaseMvvmActivity<VM extends BaseMvvmVM<? extends BaseMvvmActivity,? extends BaseMvvmModel,? extends ViewDataBinding>,B extends ViewDataBinding>
        extends AppCompatActivity  {

    public VM mViewModel;
    public B mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setLayoutToView();
        setModelToBinding();
        mViewModel = createVM();
        setVMToBinding();
        initView();
    }

    public abstract B setLayoutToView();

    public abstract void setModelToBinding();

    public abstract VM createVM();

    protected abstract void setVMToBinding();

    protected abstract void initView();
}
