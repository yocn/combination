package yocn.com.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import yocn.com.collection.R;
import yocn.com.collection.bean.MainItemBean;
import yocn.com.collection.utils.DisplayUtils;

public class MainAdapter extends BaseAdapter {
    private OnItemClickListener listener;
    private Context context;
    private ArrayList<MainItemBean> mMainItemBeanList;
    private int width;
    private LayoutInflater mInflater;

    public MainAdapter(Context context, ArrayList list) {
        mMainItemBeanList = list;
        mInflater = LayoutInflater.from(context);
        width = DisplayUtils.getScreenWidth(context);
        this.context = context;
    }

    public interface OnItemClickListener {
        public void OnItemClick(int position);
    }

    @Override
    public int getCount() {
        return mMainItemBeanList.size();
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
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_main, null);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//  取出ViewHolder对象
        }

        holder.tv_title.setText(mMainItemBeanList.get(position).getTitle());
        return convertView;
    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
        public TextView tv_title;
    }
}
