package yocn.com.collection.adapter;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import yocn.com.collection.BaseActivity;
import yocn.com.collection.R;
import yocn.com.collection.SettingAct;
import yocn.com.collection.application.MyApplication;
import yocn.com.collection.bean.DrawerSettingBean;
import yocn.com.collection.utils.Logger;

public class DrawerSettingAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private BaseActivity context;
    private ArrayList<DrawerSettingBean> mDrawerSettingBeanList = new ArrayList<DrawerSettingBean>();
    DrawerLayout drawer_layout;

    public DrawerSettingAdapter(BaseActivity context, DrawerLayout drawer_layout) {
        super();
        this.drawer_layout = drawer_layout;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<DrawerSettingBean> mSettingThemeBeanList) {
        this.mDrawerSettingBeanList = mSettingThemeBeanList;
    }

    @Override
    public int getCount() {
        return mDrawerSettingBeanList.size();
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
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_drawer_setting, null);
            holder = new ViewHolder();
            holder.tv_mode = (TextView) convertView.findViewById(R.id.tv_mode);
            holder.tb_mode = (ToggleButton) convertView.findViewById(R.id.tb_mode);
            convertView.setTag(holder);// 绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//  取出ViewHolder对象
        }
        holder.tv_mode.setText(mDrawerSettingBeanList.get(position).getTitle());
        if (position == 0) {
            holder.tb_mode.setVisibility(View.VISIBLE);
        } else {
            holder.tb_mode.setVisibility(View.GONE);
        }
        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.closeDrawers();
                switch (position) {
                    case 0:
                        /**Dark Mode*/
                        Logger.d("Dark Mode");
                        break;
                    case 1:
                        /**Settings*/
                        context.startActivity(new Intent(context, SettingAct.class));
                        break;
                    case 2:
                        /**About Me*/
                        Logger.d("About Me");
                        break;
                    default:
                        break;
                }
            }
        };
        convertView.setOnClickListener(mClickListener);
        holder.tb_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (MyApplication.isDarkMode) {
                    MyApplication.isDarkMode = false;
                } else {
                    MyApplication.isDarkMode = true;
                }
                if (MyApplication.isDarkMode) {
                    BaseActivity.TYPE_THEME = BaseActivity.TYPE_THEME_DARK;
                } else {
                    BaseActivity.TYPE_THEME = BaseActivity.TYPE_THEME_NORMAL;
                }
                context.setTheme();
                context.recreate();
            }
        });
        return convertView;
    }

    public final class ViewHolder {
        public TextView tv_mode;
        public ToggleButton tb_mode;
    }
}
