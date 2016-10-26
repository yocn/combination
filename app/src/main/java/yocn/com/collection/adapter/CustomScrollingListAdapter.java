package yocn.com.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import yocn.com.collection.R;

public class CustomScrollingListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    String[] url;

    public CustomScrollingListAdapter(Context ctx) {
        super();
        mInflater = LayoutInflater.from(ctx);

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
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.iv_item = (ImageView) convertView.findViewById(R.id.iv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(url[position], holder.iv_item);
        return convertView;
    }

    class ViewHolder {
        ImageView iv_item;
    }
}
