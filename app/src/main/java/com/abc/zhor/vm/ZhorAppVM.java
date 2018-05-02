package com.abc.zhor.vm;


import com.abc.libmvvm.command.ReplyCommand;
import com.abc.middlecommon.mvvmbase.BaseMvvmVM;
import com.abc.middlerouter.Router;
import com.abc.zhor.model.ZhorAppModel;
import com.abc.zhor.view.ZhorAppActivity;
import com.abc.zhor.databinding.ZhorActivityAppBinding;

/**
 * Created by wudi on 2018/4/28.
 */
public class ZhorAppVM extends BaseMvvmVM<ZhorAppActivity,ZhorAppModel,ZhorActivityAppBinding> {

    public ZhorAppVM(ZhorAppActivity view, ZhorAppModel model, ZhorActivityAppBinding binding) {
        super(view, model, binding);
    }

    public ReplyCommand goMainView  = new ReplyCommand(() -> Router.build("/ZhorMainActivity").go(mView)) ;

    public ReplyCommand goHomeView = new ReplyCommand(() -> Router.build("/ZhorHomeActivity").go(mView));
}
