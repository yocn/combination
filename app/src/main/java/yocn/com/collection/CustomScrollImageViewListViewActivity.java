package yocn.com.collection;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import yocn.com.collection.adapter.CustomScrollingListViewAdapter;
import yocn.com.collection.application.MyApplication;

public class CustomScrollImageViewListViewActivity extends BaseActivity {
    RelativeLayout rl_main;
    ListView lv_main;
    ImageView iv_move;
    CustomScrollingListViewAdapter mCustomScrollingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(getLayoutInflater().inflate(R.layout.activity_custom_imageview_listview, null));
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
        mCustomScrollingListAdapter = new CustomScrollingListViewAdapter(this, lv_main);
        lv_main.setAdapter(mCustomScrollingListAdapter);
    }


}
