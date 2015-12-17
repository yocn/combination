package yocn.com.collection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import yocn.com.collection.utils.SharedPreferencesUtil;

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
//        if (isFirstRun) {
        // 第一次进入
        mHandler.sendEmptyMessageDelayed(SWITCH_DUIDEACTIVITY, 0);
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
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        this.finish();
    }

    // 欢迎页面禁用back键
    @Override
    public void onBackPressed() {
    }
}
