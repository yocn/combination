package yocn.com.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Random;

import yocn.com.collection.R;
import yocn.com.collection.view.BarChartView;

public class ParaListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    public ParaListAdapter(Context context) {
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
            convertView = mInflater.inflate(R.layout.item_text, null);
            holder = new ViewHolder();
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//  取出ViewHolder对象
        }
        return convertView;
    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
    }
}
