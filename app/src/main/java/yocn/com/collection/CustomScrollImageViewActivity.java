package yocn.com.collection;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import yocn.com.collection.adapter.CustomScrollingListAdapter;
import yocn.com.collection.application.MyApplication;

public class CustomScrollImageViewActivity extends BaseActivity implements AbsListView.OnScrollListener {
    RelativeLayout rl_main;
    ListView lv_main;
    ImageView iv_move;
    CustomScrollingListAdapter mCustomScrollingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(getLayoutInflater().inflate(R.layout.activity_custom_imageview, null));
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        param.setMargins(0, MyApplication.statusHeight, 0, 0);
        toolbar.setLayoutParams(param);
//        blur();
        initView();
        initData();
    }

    private void initView() {
        iv_move = (ImageView) findViewById(R.id.iv_move);
        lv_main = (ListView) findViewById(R.id.lv_main);
    }

    private void initData() {
        mCustomScrollingListAdapter = new CustomScrollingListAdapter(this);
        lv_main.setAdapter(mCustomScrollingListAdapter);
        lv_main.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    int headerHeight;
    int minHeaderHeight;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // Y轴偏移量
        float scrollY = getScrollY(view);
        System.out.println("---------------" + scrollY);
        // 变化率
        float headerBarOffsetY = headerHeight - minHeaderHeight;// Toolbar与header高度的差值

        scrollY = scrollY > 0 ? scrollY : 0;
        float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
        System.out.println("offset--" + offset + "--scrollY---      " + scrollY);
//        iv_move.setAlpha((int) (offset * 255));
        // header背景图Y轴偏移
        if (iv_move != null) {
            iv_move.setTranslationY(-scrollY / 2);
        }
    }

    /**
     * 得到ListView在Y轴上的偏移
     */
    public float getScrollY(AbsListView view) {
        View c = view.getChildAt(0);

        if (c == null)
            return 0;

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + (firstVisiblePosition - 1) * c.getHeight();
    }
}
