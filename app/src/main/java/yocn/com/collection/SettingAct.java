package yocn.com.collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import yocn.com.collection.adapter.SettingAdapter;
import yocn.com.collection.bean.SettingBean;

public class SettingAct extends BaseActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_setting, null));
        ListView lv_setting;
        lv_setting = (ListView) findViewById(R.id.lv_setting);
        SettingAdapter adapter = new SettingAdapter(this);
        lv_setting.setAdapter(adapter);

        SettingBean mSettingBean1 = new SettingBean("default");
        SettingBean mSettingBean2 = new SettingBean("default");
        ArrayList<SettingBean> mSettingBeanList = new ArrayList<>();
        mSettingBeanList.add(mSettingBean1);
        mSettingBeanList.add(mSettingBean2);

        adapter.setData(mSettingBeanList);
        adapter.notifyDataSetChanged();

        lv_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(SettingAct.this, "default", Toast.LENGTH_SHORT).show();
                        setTheme(R.style.AppTheme);
                        MainActivity.mMainActivity.setTheme(R.style.AppTheme);
                        MainActivity.mMainActivity.recreate();
                        break;
                    case 1:
                        Toast.makeText(SettingAct.this, "red", Toast.LENGTH_SHORT).show();
                        setTheme(R.style.AppThemeRed);
                        MainActivity.mMainActivity.setTheme(R.style.AppTheme);
                        MainActivity.mMainActivity.recreate();
                        break;

                }
            }
        });
    }

}
