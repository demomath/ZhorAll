package com.abc.appmain.view;

import android.databinding.DataBindingUtil;

import com.abc.appmain.R;
import com.abc.appmain.databinding.ZhorMainActivityMainBinding;
import com.abc.appmain.model.ZhorMainModel;
import com.abc.appmain.vm.ZhorMainVM;
import com.abc.libmvvm.base.BaseMvvmActivity;
import com.abc.middlerouter.annotation.RouterAction;


@RouterAction(path = "/ZhorMainActivity")
public class ZhorMainActivity extends BaseMvvmActivity<ZhorMainVM,ZhorMainActivityMainBinding> {

    private ZhorMainModel mModel;

    @Override
    public ZhorMainActivityMainBinding setLayoutToView() {
        return DataBindingUtil.setContentView(this, R.layout.zhor_main_activity_main);
    }

    @Override
    public void bindingDefModel() {
        ZhorMainModel zhorMainModel = new ZhorMainModel("张三", 25);
        mBinding.setMain(zhorMainModel);
    }

    @Override
    public ZhorMainVM createVM() {
        return null;
    }

    @Override
    protected void bindingVM() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
