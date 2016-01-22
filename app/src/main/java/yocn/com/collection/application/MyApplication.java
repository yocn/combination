package yocn.com.collection.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

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

    }


}
