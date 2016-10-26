package yocn.com.collection.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import yocn.com.collection.R;

public class CustomScrollingListViewAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private LayoutInflater mInflater;
    String[] url;
    ListView mListView;

    public CustomScrollingListViewAdapter(Context ctx, ListView mListView) {
        super();
        mInflater = LayoutInflater.from(ctx);
        this.mListView = mListView;
        mListView.setOnScrollListener(this);
        url = new String[]{
                "http://img3.imgtn.bdimg.com/it/u=2159720437,2283443118&fm=21&gp=0.jpg",
                "http://android-screenimgs.25pp.com/uploadfile/android/images/2014/0604/20140604041940694.png",
                "http://t-1.tuzhan.com/2c6fd5e483d8/c-2/l/2013/01/28/10/3de4836016f5499a9fb92afc30bc1cba.jpg",
                "http://cdn8.staztic.com/app/a/4009/4009555/photo-filter-bokeh-effects-11-b-512x250.jpg",
                "http://img0.pconline.com.cn/pconline/1404/14/4603927_spring-flowers-3d_thumb.jpg",
                "http://attachments.gfan.com/forum/attachments2/day_111105/1111052302ca8eb4d8070f54c5.jpg"
        };

    }

    @Override
    public int getCount() {
        return url.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_with_imageview, null);
            holder = new ViewHolder();
            holder.iv_item = (ImageView) convertView.findViewById(R.id.iv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
//            holder.iv_item.setTranslationY(0);
        }
        ImageLoader.getInstance().displayImage(url[position], holder.iv_item);
//        holder.iv_item.setTranslationY(mCurrentOffSet);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    int headerHeight;
    int minHeaderHeight;
    float mCurrentOffSet = 0;

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        // Y轴偏移量
        float scrollY = getScrollY(absListView);
        System.out.println("---------------" + scrollY);
        // 变化率
        float headerBarOffsetY = headerHeight - minHeaderHeight;// Toolbar与header高度的差值

        scrollY = scrollY > 0 ? scrollY : 0;
        float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
        System.out.println("offset--" + offset + "--scrollY---      " + scrollY);
//        iv_move.setAlpha((int) (offset * 255));
        // header背景图Y轴偏移
//        if (iv_move != null) {
//            iv_move.setTranslationY(-scrollY / 2);
//        }
        mCurrentOffSet = -scrollY / 2;
        notifyDataSetInvalidated();
    }

    class ViewHolder {
        ImageView iv_item;
    }

    /**
     * 得到ListView在Y轴上的偏移
     */
    public float getScrollY(AbsListView view) {
        View c = view.getChildAt(0);

        if (c == null)
            return 0;

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + (firstVisiblePosition - 1) * c.getHeight();
    }
}
