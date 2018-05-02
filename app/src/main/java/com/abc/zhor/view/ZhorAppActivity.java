package com.abc.zhor.view;

import android.databinding.DataBindingUtil;

import com.abc.libmvvm.base.BaseMvvmActivity;
import com.abc.zhor.model.ZhorAppModel;
import com.abc.zhor.vm.ZhorAppVM;
import com.abc.zhor.R;
import com.abc.zhor.databinding.ZhorActivityAppBinding;


/**
 * Created by wudi on 2018/4/25.
 * 壳工程首页
 */
public class ZhorAppActivity extends BaseMvvmActivity<ZhorAppVM,ZhorActivityAppBinding> implements IView{

    private ZhorAppModel mModel;

    @Override
    public ZhorActivityAppBinding setLayoutToView() {
        return DataBindingUtil.setContentView(this, R.layout.zhor_activity_app);
    }

    @Override
    public void bindingDefModel() {

    }

    @Override
    public ZhorAppVM createVM() {
        return new ZhorAppVM(this,mBinding);
    }

    @Override
    protected void bindingVM() {
        mBinding.setZhorvm(mViewModel);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mViewModel.requestData();
    }
}
