package yocn.com.collection;

import android.os.Bundle;
import android.widget.ListView;

import yocn.com.collection.adapter.BarChartAdapter;
import yocn.com.collection.adapter.ParaListAdapter;

public class ParabolaViewAct extends BaseActivity {
    ListView lv_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_parabola, null));

        lv_chart = (ListView) findViewById(R.id.lv_chart);
        BarChartAdapter adapter = new BarChartAdapter(this);
        lv_chart.setAdapter(adapter);
    }

}
