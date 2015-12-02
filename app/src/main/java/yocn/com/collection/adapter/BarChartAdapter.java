package yocn.com.collection.adapter;

import java.util.Random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import yocn.com.collection.R;
import yocn.com.collection.view.BarChartView;

public class BarChartAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    public BarChartAdapter(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_bar_chart, null);
            holder = new ViewHolder();
            /** 得到各个控件的对象 */
            holder.bcv = (BarChartView) convertView.findViewById(R.id.cv_chart);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//  取出ViewHolder对象
        }
        holder.bcv.setColor(Math.abs(new Random().nextInt(255)));
        return convertView;
    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
        public BarChartView bcv;
    }
}
