package yocn.com.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import yocn.com.collection.R;
import yocn.com.collection.bean.SettingThemeBean;

public class SettingAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<SettingThemeBean> mSettingThemeBeanList = new ArrayList<SettingThemeBean>();

    public SettingAdapter(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<SettingThemeBean> mSettingThemeBeanList) {
        this.mSettingThemeBeanList = mSettingThemeBeanList;
    }

    @Override
    public int getCount() {
        return mSettingThemeBeanList.size();
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
            holder.view = (View) convertView.findViewById(R.id.view);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//  取出ViewHolder对象
        }
        holder.tv_setting.setText(mSettingThemeBeanList.get(position).getTitle());
        holder.view.setBackgroundResource(mSettingThemeBeanList.get(position).getColor());
        return convertView;
    }

    public final class ViewHolder {
        public TextView tv_setting;
        public View view;
    }
}
