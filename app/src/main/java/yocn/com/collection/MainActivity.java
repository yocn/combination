package yocn.com.collection;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import yocn.com.collection.adapter.MainAdapter;
import yocn.com.collection.bean.MainItemBean;


public class MainActivity extends BaseActivity {
    RecyclerView rv_main;
    ArrayList mMainItemBeanList = new ArrayList<MainItemBean>();
    DrawerLayout drawer_layout;
    //声明相关变量
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_main, null));
        init();
        initDrawerLayout();
        initScrollView();
    }

    private void init() {
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout.openDrawer(Gravity.LEFT);
    }

    private void initDrawerLayout() {
        toolbar.setTitle("Combition");
        setSupportActionBar(toolbar);
        rv_main = (RecyclerView) findViewById(R.id.rv_main);
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
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        lvLeftMenu.setAdapter(arrayAdapter);
    }

    private void initScrollView() {
        String[] mTitles = new String[]{"RippleView", "ChartView", "BarChartView", "ParabolaView", "Path", "RecycleView"};
        Class[] mTarget = new Class[]{RippleViewActivity.class, ChartViewAct.class, BarChartViewAct.class, ParabolaViewAct.class, PathActivity.class, RecycleViewAct.class};
        MainItemBean mMainItemBean;
        for (int i = 0; i < mTarget.length; i++) {
            mMainItemBean = new MainItemBean(mTitles[i], mTarget[i]);
            mMainItemBeanList.add(mMainItemBean);
        }
        MainAdapter mMainAdapter = new MainAdapter(this, mMainItemBeanList);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        rv_main.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        rv_main.setHasFixedSize(true);
        rv_main.setAdapter(mMainAdapter);
    }

}
