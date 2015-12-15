package yocn.com.collection.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import yocn.com.collection.R;
import yocn.com.collection.bean.MainItemBean;
import yocn.com.collection.utils.Logger;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private OnItemClickListener listener;
    private Context context;
    private ArrayList<MainItemBean> mMainItemBeanList;

    public MainAdapter(Context context, ArrayList list) {
        mMainItemBeanList = list;
        this.context = context;
    }

    public interface OnItemClickListener {
        public void OnItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 给ViewHolder设置布局文件
        Logger.d("onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 给ViewHolder设置元素
        holder.tv_title.setText(mMainItemBeanList.get(position).getTitle());
//        holder.card_view.setBackgroundResource(R.color.actionbar_bg);
    }

    @Override
    public int getItemCount() {
        return mMainItemBeanList.size();
    }

    // 重写的自定义ViewHolder
    public class ViewHolder
            extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public CardView card_view;

        public ViewHolder(View v) {
            super(v);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
            card_view = (CardView) v.findViewById(R.id.card_view);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, mMainItemBeanList.get(getPosition()).getTarget()));
                }
            });
        }
    }
}
