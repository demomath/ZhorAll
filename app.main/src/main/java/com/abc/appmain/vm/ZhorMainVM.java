package com.abc.appmain.vm;

import com.abc.appmain.view.ZhorMainActivity;
import com.abc.appmain.databinding.ZhorMainActivityMainBinding;
import com.abc.appmain.model.ZhorMainModel;
import com.abc.libmvvm.base.BaseMvvmModel;
import com.abc.libmvvm.base.BaseMvvmVM;

/**
 * Created by wudi on 2018/4/27.
 */

public class ZhorMainVM extends BaseMvvmVM<ZhorMainActivity,ZhorMainModel,ZhorMainActivityMainBinding> {

    public ZhorMainVM(ZhorMainActivity view, ZhorMainActivityMainBinding binding) {
        super(view, binding);
    }

    @Override
    public void onSuccess(ZhorMainModel zhorMainModel) {

    }

    @Override
    public void onFail(String code, String message) {

    }
}
