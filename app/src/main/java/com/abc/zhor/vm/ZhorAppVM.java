package com.abc.zhor.vm;


import android.databinding.ObservableField;

import com.abc.libmvvm.base.BaseMvvmVM;
import com.abc.libmvvm.command.ReplyCommand;
import com.abc.middlerouter.Router;
import com.abc.zhor.databinding.ZhorActivityAppBinding;
import com.abc.zhor.model.ZhorAppModel;
import com.abc.zhor.net.ZhorAppModelManager;
import com.abc.zhor.view.ZhorAppActivity;

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
    }

    @Override
    public void onSuccess(ZhorAppModel zhorAppModel) {
        ObservableField<String> observableField = new ObservableField<>();
        observableField.set("https://www.baidu.com/s?wd=%E4%BB%8A%E6%97%A5%E6%96%B0%E9%B2%9C%E4%BA%8B&tn=SE_PclogoS_8whnvm25&sa=ire_dl_gh_logo&rsv_dl=igh_logo_pcs");
        mModel = new ZhorAppModel("111","222",observableField);
        mBinding.setZhormodel(mModel);
    }

    @Override
    public void onFail(String code, String message) {

    }
}
