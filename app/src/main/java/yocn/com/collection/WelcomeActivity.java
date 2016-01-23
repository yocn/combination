package yocn.com.collection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import yocn.com.collection.application.MyApplication;
import yocn.com.collection.utils.SharedPreferencesUtil;
import yocn.com.collection.utils.Utils;

public class WelcomeActivity extends Activity {

    private SharedPreferencesUtil mSpUtil;
    View main;
    public static final int APP_NOW_START = 1001;
    public static final int SWITCH_DUIDEACTIVITY = 1003;
    public static boolean isFirstRun;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTrans();
        main = getLayoutInflater().inflate(R.layout.activity_welcome, null);
        main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(main);
        mSpUtil = new SharedPreferencesUtil(this, "setting");
        isFirstRun = mSpUtil.getBoolean("isFirstRun", true);
        MyApplication.statusHeight = Utils.getStatusBarHeight(this);
        mHandler.sendEmptyMessageDelayed(SWITCH_DUIDEACTIVITY, 1000);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SWITCH_DUIDEACTIVITY:
                    mSpUtil.saveBoolean("isFirstRun", false);
                    goHome();
                    break;
                case APP_NOW_START:
                    goHome();
                    break;
            }
        }

        ;
    };

    /**
     * 设置状态栏和导航栏透明
     */
    private void setTrans() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 去首页
     */
    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }


    // 欢迎页面禁用back键
    @Override
    public void onBackPressed() {
    }
}
