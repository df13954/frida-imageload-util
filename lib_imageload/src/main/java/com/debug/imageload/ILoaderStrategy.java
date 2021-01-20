package com.debug.imageload;

/**
 * @time 2021/1/20 14:21
 * @description 具体框架需要实现的接口
 */
public interface ILoaderStrategy {
    /**
     * 自己使用某个具体框架来加载图片的实现。
     * 取出option中的属性，进行加载。
     *
     * @param options 包含地址，view，展位图等其他配置数据
     */
    void loadImage(LoaderOptions options);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache();

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache();


}
