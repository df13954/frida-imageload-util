package com.debug.imageload;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import androidx.annotation.DrawableRes;

import java.io.File;

/**
 * 该类为图片加载框架的通用属性封装，不能耦合任何一方的框架
 * 这个属性，就是我们平时加载图片的时候配置的属性
 * 基本属性：image path；image view；
 * 其他：加载占位符，错误占位符。
 * 图片圆角：glide实现有点问题，有时候不显示圆角，或者先显示方，闪一下显示圆角。
 * 图片圆角：建议直接使用圆角的img。
 */
public class LoaderOptions {
    //占位图
    public int placeholderResId;
    //错误图片
    public int errorResId;
    public boolean isCenterCrop;
    public boolean isCenterInside;
    public boolean skipNetCache = false;
    //内存缓存
    public boolean skipLocalCache = false;
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public int targetWidth;
    public int targetHeight;
    //圆角角度
    public float degrees;
    //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public float bitmapAngle;
    public Drawable placeholder;
    //targetView展示图片
    public View targetView;
    public BitmapCallBack callBack;
    public String url;
    public File file;
    //本地的drawable
    public int drawableResId;
    public Uri uri;
    //需要加载的bitmap
    public Bitmap targetBitmap;
    //加载byte数据
    public byte[] targetByte;
    //实时切换图片加载库
    public ILoaderStrategy loader;

    //加载资源，是否gif
    public boolean loadGif = false;

    public LoaderOptions(boolean loadGif, int res) {
        this.loadGif = loadGif;
        this.drawableResId = res;
    }

    public LoaderOptions(byte[] targetByte) {
        this.targetByte = targetByte;
    }

    public LoaderOptions(Bitmap bitmap) {
        this.targetBitmap = bitmap;
    }

    public LoaderOptions(String url) {
        this.url = url;
    }

    public LoaderOptions(File file) {
        this.file = file;
    }

    public LoaderOptions(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public LoaderOptions(Uri uri) {
        this.uri = uri;
    }

    public void into(View targetView) {
        this.targetView = targetView;
        ImageLoader.getInstance().loadOptions(this);
    }

    public void bitmap(BitmapCallBack callBack) {
        this.callBack = callBack;
        ImageLoader.getInstance().loadOptions(this);
    }

    public LoaderOptions loader(ILoaderStrategy imageLoader) {
        this.loader = imageLoader;
        return this;
    }

    public LoaderOptions placeholder(@DrawableRes int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoaderOptions placeholder(Drawable placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public LoaderOptions error(@DrawableRes int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public LoaderOptions centerCrop() {
        isCenterCrop = true;
        return this;
    }

    public LoaderOptions centerInside() {
        isCenterInside = true;
        return this;
    }

    public LoaderOptions bitmapConfig(Bitmap.Config config) {
        this.config = config;
        return this;
    }

    public LoaderOptions resize(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    /**
     * 圆角
     *
     * @param bitmapAngle 度数
     * @return
     */
    public LoaderOptions angle(float bitmapAngle) {
        this.bitmapAngle = bitmapAngle;
        return this;
    }

    public LoaderOptions skipLocalCache(boolean skipLocalCache) {
        this.skipLocalCache = skipLocalCache;
        return this;
    }

    public LoaderOptions skipNetCache(boolean skipNetCache) {
        this.skipNetCache = skipNetCache;
        return this;
    }

    public LoaderOptions rotate(float degrees) {
        this.degrees = degrees;
        return this;
    }

}


