package com.abc.zhor.net;

import com.abc.libmvvm.base.BaseMvvmModel;
import com.abc.zhor.model.ZhorAppModel;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wudi on 2018/5/2.
 */

public interface ZhorAppApi {

    /**
     * 测试
     * @param Body
     * @return
     */
    @POST("/path")
    Observable<BaseMvvmModel<ZhorAppModel>> test(@Body RequestBody Body);
}
