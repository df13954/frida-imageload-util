package com.android.fridaimageload;

import android.app.Application;

import com.debug.imageload.ImageLoader;

/**
 * @author: dr
 * @date: 2021/1/20
 * @description $app
 */
public class App extends Application {

    public static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        //初始化glide 如果需要换其他，自行实现ILoaderStrategy
        ImageLoader.getInstance().setGlobalImageLoader(new GlideLoaderImp());
    }
}