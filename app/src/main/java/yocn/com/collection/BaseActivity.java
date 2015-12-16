package yocn.com.collection;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import yocn.com.collection.application.MyApplication;
import yocn.com.collection.utils.Logger;
import yocn.com.collection.utils.SystemBarTintManager;
import yocn.com.collection.view.SlideRightOutView;

/**
 * Created by Yocn on 2015/12/2 0002.
 */
public class BaseActivity extends ActionBarActivity {
    Toolbar toolbar;
    public static int[] styles = {R.style.AppTheme, R.style.AppThemeRed, R.style.AppThemeMaterial};
    public static int TYPE_THEME = 0;
    public static final int TYPE_THEME_BASE = 0;
    public static final int TYPE_THEME_RED = 1;
    public static final int TYPE_THEME_MATERIAL = 2;
    public static int TYPE_MAIN_STYLE = 0;
    public static final int TYPE_MAIN_HARI = 0;
    public static final int TYPE_MAIN_SQUARE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(BaseActivity.styles[BaseActivity.TYPE_THEME]);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addActivity2List(this);
        super.setContentView(view);
    }

    @Override
    protected void onDestroy() {
        removeFromActivityList(this);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 设置返回键杀掉自己
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addActivity2List(Activity act) {
        MyApplication.actitivitys.add(act);
    }

    public void removeFromActivityList(Activity act) {
        MyApplication.actitivitys.remove(act);
    }

}
