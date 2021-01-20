package com.debug.imageload;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;

/**
 * 图片加载类
 * 策略或者静态代理模式，开发者只需要关心ImageLoader + LoaderOptions
 * 加载option中的内容即可
 */

public class ImageLoader {

    private static ILoaderStrategy sLoader;
    private static volatile ImageLoader sInstance;

    private ImageLoader() {
    }

    //单例模式
    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }

    /**
     * 提供全局替换图片加载框架的接口，若切换其它框架，可以实现一键全局替换
     * application中初始化调用
     */
    public void setGlobalImageLoader(ILoaderStrategy loader) {
        sLoader = loader;
    }

    public LoaderOptions loadGif(int gif) {
        return new LoaderOptions(true, gif);
    }

    public LoaderOptions load(byte[] bytes) {
        return new LoaderOptions(bytes);
    }

    public LoaderOptions load(Bitmap bitmap) {
        return new LoaderOptions(bitmap);
    }

    /**
     * @param url 加载远程地址，本地drawable，本地file，uri等等
     * @return LoaderOptions
     */
    public LoaderOptions load(String url) {
        return new LoaderOptions(url);
    }

    public LoaderOptions load(int drawable) {
        return new LoaderOptions(drawable);
    }

    public LoaderOptions load(File file) {
        return new LoaderOptions(file);
    }

    public LoaderOptions load(Uri uri) {
        return new LoaderOptions(uri);
    }

    /**
     * 优先使用实时设置的图片loader，其次使用全局设置的图片loader
     */
    public void loadOptions(LoaderOptions options) {
        if (options.loader != null) {
            options.loader.loadImage(options);
        } else {
            checkNotNull();
            sLoader.loadImage(options);
        }
    }

    public void clearMemoryCache() {
        checkNotNull();
        sLoader.clearMemoryCache();
    }

    public void clearDiskCache() {
        checkNotNull();
        sLoader.clearDiskCache();
    }

    private void checkNotNull() {
        if (sLoader == null) {
            throw new NullPointerException("you must be set your imageLoader at first!");
        }
    }
}
