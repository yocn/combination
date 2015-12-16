package yocn.com.collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import yocn.com.collection.adapter.SettingAdapter;
import yocn.com.collection.bean.SettingThemeBean;
import yocn.com.collection.utils.Logger;

public class SettingAct extends SildeDoneBaseActivity {

    private ArrayList<SettingThemeBean> mSettingThemeBeanList = new ArrayList<SettingThemeBean>();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_setting, null));
        ListView lv_setting;
        lv_setting = (ListView) findViewById(R.id.lv_setting);
        SettingAdapter adapter = new SettingAdapter(this);
        lv_setting.setAdapter(adapter);

        String[] strings = {"blue theme", "red theme", "material theme"};
        int[] ints = {R.color.blue_theme, R.color.red_theme, R.color.material_theme};
        for (int i = 0; i < strings.length; i++) {
            SettingThemeBean mSettingThemeBean = new SettingThemeBean(strings[i], ints[i]);
            mSettingThemeBeanList.add(mSettingThemeBean);
        }
        adapter.setData(mSettingThemeBeanList);
        adapter.notifyDataSetChanged();

        lv_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(SettingAct.this, "default", Toast.LENGTH_SHORT).show();
                        MainActivity.TYPE_THEME = BaseActivity.TYPE_THEME_BASE;
                        MainActivity.mMainActivity.recreate();
                        SettingAct.this.recreate();
                        break;
                    case 1:
                        Toast.makeText(SettingAct.this, "red", Toast.LENGTH_SHORT).show();
                        MainActivity.TYPE_THEME = BaseActivity.TYPE_THEME_RED;
                        MainActivity.mMainActivity.recreate();
                        SettingAct.this.recreate();
                        break;
                    case 2:
                        Toast.makeText(SettingAct.this, "material", Toast.LENGTH_SHORT).show();
                        MainActivity.TYPE_THEME = BaseActivity.TYPE_THEME_MATERIAL;
                        MainActivity.mMainActivity.recreate();
                        SettingAct.this.recreate();
                        break;
                    default:
                        Logger.d("123456789");
                        break;
                }
            }
        });
    }

}
