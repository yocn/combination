package yocn.com.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import yocn.com.collection.adapter.DrawerSettingAdapter;
import yocn.com.collection.adapter.MainRecycleAdapter;
import yocn.com.collection.bean.DrawerSettingBean;
import yocn.com.collection.bean.MainItemBean;
import yocn.com.collection.utils.Logger;

public class MainActivity extends BaseActivity {
    private RecyclerView rv_main;
    private ArrayList<MainItemBean> mMainItemBeanList = new ArrayList<MainItemBean>();
    private ArrayList<DrawerSettingBean> mDrawerSettingBeanList = new ArrayList<DrawerSettingBean>();
    private DrawerLayout drawer_layout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lv_drawer;
    private String[] drawerStrings = {"DarkMode", "Setting", "About Us"};
    private MainRecycleAdapter mMainAdapter;
    private MainItemBean mMainItemBean;
    private DrawerSettingAdapter mDrawerSettingAdapter;
    private DrawerSettingBean mDrawerSettingBean;
    public static MainActivity mMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = MainActivity.this;
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(view);
        initView();
        initDrawerLayout();
        initScrollView();
    }

    private void initView() {
        rv_main = (RecyclerView) findViewById(R.id.rv_main);
        lv_drawer = (ListView) findViewById(R.id.lv_left_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void initDrawerLayout() {
        toolbar.setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.action_settings, R.string.action_settings) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        drawer_layout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        mDrawerSettingAdapter = new DrawerSettingAdapter(this);
        for (int i = 0; i < drawerStrings.length; i++) {
            mDrawerSettingBean = new DrawerSettingBean(drawerStrings[i]);
            mDrawerSettingBeanList.add(mDrawerSettingBean);
        }
        mDrawerSettingAdapter.setData(mDrawerSettingBeanList);
        lv_drawer.setAdapter(mDrawerSettingAdapter);
        lv_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        goSettingActivity();
                        break;
                    case 1:
                        break;
                }
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
    }

    private void initScrollView() {
        String[] mTitles = new String[]{"RippleView", "ChartView", "BarChartView", "ParabolaView", "Path", "RecycleView"};
        Class[] mTarget = new Class[]{RippleViewActivity.class, ChartViewAct.class, BarChartViewAct.class, ParabolaViewAct.class, PathActivity.class, RecycleViewAct.class};

        for (int i = 0; i < mTarget.length; i++) {
            mMainItemBean = new MainItemBean(mTitles[i], mTarget[i]);
            mMainItemBeanList.add(mMainItemBean);
        }

        mMainAdapter = new MainRecycleAdapter(this, mMainItemBeanList);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        rv_main.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        rv_main.setHasFixedSize(true);
        rv_main.setAdapter(mMainAdapter);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_settings:
                    goSettingActivity();
                    break;
                case android.R.id.home:
                    goSettingActivity();
                    Logger.d("123456789");
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goSettingActivity() {
        Intent intent = new Intent(this, SettingAct.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
