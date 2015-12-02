package yocn.com.collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import yocn.com.collection.adapter.BarChartAdapter;

public class BarChartViewAct extends BaseActivity {


    private ListView lv_chart;
    private BarChartAdapter mBarChartAdapter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_bar_chart, null));
        lv_chart = (ListView) findViewById(R.id.lv_chart);
        mBarChartAdapter = new BarChartAdapter(this);
        lv_chart.setAdapter(mBarChartAdapter);
    }

}
