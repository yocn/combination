package yocn.com.collection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import yocn.com.collection.application.MyApplication;
import yocn.com.collection.utils.SharedPreferencesUtil;
import yocn.com.collection.utils.Utils;

public class WelcomeActivity extends Activity {
    boolean isFirstIn = false;
    private long SPLASH_DELAY_MILLIS = 1000;

    private SharedPreferencesUtil mSpUtil;

    public static final int APP_NOW_START = 1001;
    public static final int APP_DELAY_START = 1002;
    public static final int SWITCH_DUIDEACTIVITY = 1003;
    public static boolean isFirstRun;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_welcome, null));
        mSpUtil = new SharedPreferencesUtil(this, "setting");
        isFirstRun = mSpUtil.getBoolean("isFirstRun", true);
        MyApplication.statusHeight = Utils.getStatusBarHeight(this);
//        hideNaviBar();
//        if (isFirstRun) {
        // 第一次进入
        mHandler.sendEmptyMessageDelayed(SWITCH_DUIDEACTIVITY, 3000);
//        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SWITCH_DUIDEACTIVITY:
                    mSpUtil.saveBoolean("isFirstRun", false);
//                    Intent intent = new Intent(WelcomeActivity.this, ViewPagerActivity.class);
//                    startActivity(intent);
//                    WelcomeActivity.this.finish();
//                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_left);
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
     * 去首页
     */
    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        this.finish();
    }

    /**
     * 隐藏底部导航栏
     */
    private void hideNaviBar(){
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    // 欢迎页面禁用back键
    @Override
    public void onBackPressed() {
    }
}
