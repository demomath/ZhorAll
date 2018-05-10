package com.abc.middlecommon;

import android.app.usage.UsageEvents;
import android.databinding.ViewDataBinding;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.abc.libcore.rxtools.RxKeyboardTool;
import com.abc.libmvvm.base.BaseMvvmActivity;
import com.abc.libmvvm.base.BaseMvvmVM;

/**
 * Created by wudi on 2018/5/9.
 */

public class CommonActivity<VM extends BaseMvvmVM<? extends BaseMvvmActivity,?,? extends ViewDataBinding>,B extends ViewDataBinding> extends BaseMvvmActivity {

    @Override
    public ViewDataBinding setLayoutToView() {
        return null;
    }

    @Override
    public void bindingDefModel() {

    }

    @Override
    public BaseMvvmVM<? extends BaseMvvmActivity, ?, ? extends ViewDataBinding> createVM() {
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

    /**
     * 点击空白处隐藏软键盘
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent (MotionEvent event){
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
            RxKeyboardTool.hideSoftInput(this);
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }


}
