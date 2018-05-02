package com.abc.apphome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.abc.libcore.rxtools.view.RxToast;
import com.abc.middlerouter.annotation.RouterAction;
import com.abc.thor_home.R;


@RouterAction(path = "/ZhorHomeActivity")
public class ZhorHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhor_home_activity_home);
        RxToast.info(this,"HOME", Toast.LENGTH_SHORT,true).show();
    }
}
