package yocn.com.collection;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import yocn.com.collection.adapter.BarChartAdapter;
import yocn.com.collection.utils.SharedPreferencesUtil;
import yocn.com.collection.view.ParabolaView;
import yocn.com.collection.view.Pull2RefreshList;

public class ParabolaViewAct extends SildeDoneBaseActivity implements Pull2RefreshList.OnFreshListener {
    Pull2RefreshList lv_chart;
    ParabolaView parabolaView;
    BarChartAdapter adapter;
    SharedPreferencesUtil util;
    boolean isFisstPara = false;
    RelativeLayout rl_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_parabola, null));

        util = SharedPreferencesUtil.getInstance(this);
        isFisstPara = util.getBoolean("firstPara", true);
        lv_chart = (Pull2RefreshList) findViewById(R.id.lv_chart);
        parabolaView = (ParabolaView) findViewById(R.id.para);
        /**盖的提示层*/
        rl_hint = (RelativeLayout) findViewById(R.id.rl_hint);
        rl_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_hint.setVisibility(View.GONE);
            }
        });
        if (isFisstPara) {
            rl_hint.setVisibility(View.VISIBLE);
        }
        lv_chart.setOnFreshListener(this);

        adapter = new BarChartAdapter(this);
        lv_chart.setAdapter(adapter);
    }

    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        util.saveBoolean("firstPara", false);
        super.onDestroy();
    }
}
