package com.abc.middlerouter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.abc.libcore.rxtools.RxLogTool;
import com.abc.middlerouter.annotation.RouterAction;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * Created by wudi on 2018/4/25.
 * 路由控制，维护uri的list
 */
public class RouterManager {

    private volatile static RouterManager sRouterManager;

    private ArrayList<Uri> mList = new ArrayList<>();

    private RouterManager() {}

    public static RouterManager getInstance () {
        if (sRouterManager == null) {
            synchronized (RouterManager.class) {
                if (sRouterManager == null) {
                    sRouterManager = new RouterManager();
                }
            }
        }
        return sRouterManager;
    }

    public List<Uri> init (Context context) {
        try {
            //通过资源路径获得DexFile
            DexFile e = new DexFile(context.getPackageResourcePath());
            Enumeration entries = e.entries();
            //遍历所有元素
            while(entries.hasMoreElements()) {
                String entryName = (String)entries.nextElement();
                //匹配Activity包名与类名
                if(entryName.contains("Activity")) {
                    //通过反射获得Activity类
                    Class entryClass = Class.forName(entryName);
                    if(entryClass.isAnnotationPresent(RouterAction.class)) {
                        RouterAction action = (RouterAction)entryClass.getAnnotation(RouterAction.class);

                        String from = action.from();

                        String scheme = action.scheme();
                        String host = action.host();
                        String path = action.path();
                        if (TextUtils.isEmpty(path)) {
                            RxLogTool.e(entryName+"don't has path");
                            continue;
                        }
                        String uriStr = scheme+"://"+host+path;
                        RxLogTool.e(uriStr);
                        this.mList.add(Uri.parse(uriStr));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mList;
    }

    ArrayList<Uri> getList() {
        return mList;
    }

}
