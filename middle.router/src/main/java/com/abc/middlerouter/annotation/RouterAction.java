package com.abc.middlerouter.annotation;

import android.support.annotation.NonNull;

import com.abc.middlerouter.common.RouterConstant;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Description: 路由跳转界面  注解
 * Created by wudi on 2018/1/10.
 */
@Target(ElementType.TYPE) //注解作用于类型（类，接口，注解，枚举）
@Retention(RetentionPolicy.RUNTIME) //运行时保留，运行中可以处理
@Documented  //生成javadoc文件
public @interface RouterAction {

    String from() default RouterConstant.DEFAULT_FROM;
    String scheme() default RouterConstant.DEFAULT_SCHEME;
    String host() default RouterConstant.DEFAULT_HOST;
    @NonNull String path();
}
