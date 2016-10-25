package yocn.com.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import yocn.com.collection.R;

/**
 * 动画进来的时候就执行，但是现在的问题是 刷新的时候会刷新掉原来的动画的状态
 * <p/>
 * 1、设置flag，不刷新原来的动画状态
 */
public class GradientAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private LinkedList<String> mStringList;
    Animation animation;
    ArrayList<View> viewList = new ArrayList<View>();

    public GradientAdapter(Context context, LinkedList<String> mStringList) {
        super();
        this.mContext = context;
        this.mStringList = mStringList;
        animation = AnimationUtils.loadAnimation(context, R.anim.gradually);
        animation.setFillAfter(true);
        mInflater = LayoutInflater.from(context);
        for (int i = 0; i < 7; i++) {
            View view = mInflater.inflate(R.layout.item_up_view_type_chat, null);
            viewList.add(view);
        }
    }

    public void setData(LinkedList<String> mStringList) {
        this.mStringList = mStringList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mStringList.size();
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
        convertView = viewList.get(position);
        holder = new ViewHolder();
        holder.rl_bg = (RelativeLayout) convertView.findViewById(R.id.rl_bg);
        holder.tv_setting = (TextView) convertView.findViewById(R.id.tv_setting);
        convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.rl_bg.clearAnimation();
        }
        holder.rl_bg.setBackgroundResource(R.drawable.gray_round_conner_half_trans);
        holder.tv_setting.setTextColor(mContext.getResources().getColor(R.color.white));
        holder.tv_setting.setText(mStringList.get(position) + "-position-" + position + "-hash-" + holder.tv_setting.hashCode());
//        if (position == mStringList.size() - 1) {
            /**
             * position   size
             * 0          1
             * 0 1        2
             */
//            holder.rl_bg.startAnimation(animation);
            System.out.println("position--" + position);
            System.out.println("mStringList.size()--" + mStringList.size());
//        }
        return convertView;
    }


    /**
     * 存放控件
     */
    public final class ViewHolder {
        RelativeLayout rl_bg;
        TextView tv_setting;
    }
}
