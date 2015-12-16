package yocn.com.collection;

import android.os.Bundle;

import yocn.com.collection.adapter.BarChartAdapter;
import yocn.com.collection.view.ParabolaView;
import yocn.com.collection.view.Pull2RefreshList;

public class ParabolaViewAct extends SildeDoneBaseActivity implements Pull2RefreshList.OnFreshListener {
    Pull2RefreshList lv_chart;
    ParabolaView parabolaView;
    BarChartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_parabola, null));

        lv_chart = (Pull2RefreshList) findViewById(R.id.lv_chart);
        parabolaView = (ParabolaView) findViewById(R.id.para);
        lv_chart.setOnFreshListener(this);

        adapter = new BarChartAdapter(this);
        lv_chart.setAdapter(adapter);
    }

    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }
}
