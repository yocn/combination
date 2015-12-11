package yocn.com.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import yocn.com.collection.R;
import yocn.com.collection.bean.SettingBean;
import yocn.com.collection.view.BarChartView;

public class SettingAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    ArrayList<SettingBean> mSettingBeanList = new ArrayList<>();

    public SettingAdapter(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<SettingBean> mSettingBeanList) {
        this.mSettingBeanList = mSettingBeanList;
    }

    @Override
    public int getCount() {
        return mSettingBeanList.size();
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
            convertView = mInflater.inflate(R.layout.item_setting, null);
            holder = new ViewHolder();
            holder.tv_setting = (TextView) convertView.findViewById(R.id.tv_setting);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//  取出ViewHolder对象
        }
        holder.tv_setting.setText(mSettingBeanList.get(position).getTitle());
        return convertView;
    }

    public final class ViewHolder {
        public TextView tv_setting;
    }
}
