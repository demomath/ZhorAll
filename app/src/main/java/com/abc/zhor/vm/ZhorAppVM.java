package com.abc.zhor.vm;


import com.abc.libmvvm.base.BaseMvvmModel;
import com.abc.libmvvm.base.BaseMvvmVM;
import com.abc.libmvvm.command.ReplyCommand;
import com.abc.libnet.RetrofitManager;
import com.abc.middlerouter.Router;
import com.abc.zhor.model.ZhorAppModel;
import com.abc.zhor.net.ZhorAppModelManager;
import com.abc.zhor.view.ZhorAppActivity;
import com.abc.zhor.databinding.ZhorActivityAppBinding;

/**
 * Created by wudi on 2018/4/28.
 */
public class ZhorAppVM extends BaseMvvmVM<ZhorAppActivity,ZhorAppModel,ZhorActivityAppBinding> {

    public ZhorAppVM(ZhorAppActivity view, ZhorActivityAppBinding binding) {
        super(view, binding);
    }

    public ReplyCommand goMainView  = new ReplyCommand(() -> Router.build("/ZhorMainActivity").go(mView)) ;

    public ReplyCommand goHomeView = new ReplyCommand(() -> Router.build("/ZhorHomeActivity").go(mView));


    public void requestData() {
        onSuccess(null);

        ZhorAppModelManager.getInstance().test(null,this);
//        RetrofitManager.create();
    }

    @Override
    public void onSuccess(ZhorAppModel zhorAppModel) {
        mModel = new ZhorAppModel("111","222");
        mBinding.setZhormodel(mModel);
    }

    @Override
    public void onFail(String code, String message) {

    }
}
