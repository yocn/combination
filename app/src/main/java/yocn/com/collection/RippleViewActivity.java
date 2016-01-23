package yocn.com.collection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import yocn.com.collection.application.MyApplication;
import yocn.com.collection.utils.DisplayUtils;

public class RippleViewActivity extends BaseActivity {
    Bitmap b = null;
    RelativeLayout rl_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(getLayoutInflater().inflate(R.layout.activity_ripple_view, null));
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        param.setMargins(0, MyApplication.statusHeight, 0, 0);
        toolbar.setLayoutParams(param);
        blur();
    }

    private void blur() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                b = DisplayUtils.blurBitmap(RippleViewActivity.this, BitmapFactory.decodeResource(RippleViewActivity.this.getResources(), R.drawable.icon));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                rl_main.setBackground(new BitmapDrawable(b));
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

}
