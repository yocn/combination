package yocn.com.collection;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import yocn.com.collection.application.MyApplication;
import yocn.com.collection.utils.Logger;
import yocn.com.collection.utils.SharedPreferencesUtil;
import yocn.com.collection.utils.SystemBarTintManager;
import yocn.com.collection.view.SlideRightOutView;

/**
 * Created by Yocn on 2015/12/2 0002.
 */
public class BaseActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private SharedPreferencesUtil util;
    public static int[] styles = {R.style.AppTheme, R.style.AppThemeRed, R.style.AppThemeMaterial, R.style.DarkMode};
    public static int TYPE_THEME = 0;
    public static int TYPE_THEME_NORMAL = 0;
    public static final int TYPE_THEME_BASE = 0;
    public static final int TYPE_THEME_RED = 1;
    public static final int TYPE_THEME_MATERIAL = 2;
    public static final int TYPE_THEME_DARK = 3;

    public static int TYPE_MAIN_STYLE = 0;
    public static final int TYPE_MAIN_HARI = 0;
    public static final int TYPE_MAIN_SQUARE = 1;

    public static int color = 0;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        util = SharedPreferencesUtil.getInstance(this);
        setTheme();
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

    public void setTheme() {
        util.saveInt("theme", BaseActivity.TYPE_THEME);
        if (TYPE_THEME != TYPE_THEME_DARK) {
            TYPE_THEME_NORMAL = BaseActivity.TYPE_THEME;
        }
        getColor();
        setTheme(BaseActivity.styles[BaseActivity.TYPE_THEME]);
    }

    private void getColor() {
        TypedValue typedValue = new TypedValue();
        this.getTheme().resolveAttribute(BaseActivity.styles[BaseActivity.TYPE_THEME], typedValue, true);
        int[] attribute = new int[]{android.R.attr.colorPrimary};
        TypedArray array = this.obtainStyledAttributes(typedValue.resourceId, attribute);
//        int textSize = array.getDimensionPixelSize(0 /* index */, -1 /* default size */);
        color = array.getColor(0, getResources().getColor(R.color.blue_theme));

        Logger.d("color--" + color);
        array.recycle();
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
