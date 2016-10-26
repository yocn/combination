package yocn.com.collection.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.ArrayList;

import yocn.com.collection.BaseActivity;
import yocn.com.collection.utils.SharedPreferencesUtil;
import yocn.com.collection.utils.Utils;

/**
 * Created by JD on 2015/12/11 0011.
 */
public class MyApplication extends Application {
    public static int styleIndex = 0;
    public static int statusHeight;
    public static ArrayList<Activity> actitivitys = new ArrayList<>();
    public static boolean isDarkMode = false;
    private SharedPreferencesUtil util;

    @Override
    public void onCreate() {
        util = SharedPreferencesUtil.getInstance(this);
        BaseActivity.TYPE_THEME_NORMAL =  BaseActivity.TYPE_THEME = util.getInt("theme", BaseActivity.TYPE_THEME_BASE);
        super.onCreate();
        initImageLoader();
    }

    /**
     * 初始化图片缓存
     */
    private void initImageLoader() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(getImageDownloadConfig());
    }

    private ImageLoaderConfiguration getImageDownloadConfig() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration mInnerImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(10).denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(defaultOptions).diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).diskCacheSize(Integer.MAX_VALUE).diskCacheFileCount(Integer.MAX_VALUE)
                // 缓存到文件的最大数据
                .imageDownloader(DefaultConfigurationFactory.createImageDownloader(this))
                // .memoryCache(new LruMemoryCache(2* 1024 * 1024))
                .memoryCache(new WeakMemoryCache()).memoryCacheSizePercentage(13).memoryCacheSize(2 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        return mInnerImageLoaderConfiguration;
    }
}
