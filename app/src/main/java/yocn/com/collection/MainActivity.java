package yocn.com.collection;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import yocn.com.collection.adapter.MainAdapter;
import yocn.com.collection.bean.MainItemBean;


public class MainActivity extends BaseActivity {
    Toolbar toolbar;
    RecyclerView rv_main;
    ArrayList mMainItemBeanList = new ArrayList<MainItemBean>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_main, null));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Combition");
        setSupportActionBar(toolbar);
        rv_main = (RecyclerView) findViewById(R.id.rv_main);
        String[] mTitles = new String[]{"RippleView", "ChartView", "BarChartView", "ParabolaView", "Path", "RecycleView"};
        Class[] mTarget = new Class[]{RippleViewActivity.class, ChartViewAct.class, BarChartViewAct.class, ParabolaViewAct.class, PathActivity.class, RecycleViewAct.class};
        MainItemBean mMainItemBean;
        for (int i = 0; i < mTarget.length; i++) {
            mMainItemBean = new MainItemBean(mTitles[i], mTarget[i]);
            mMainItemBeanList.add(mMainItemBean);
        }
        MainAdapter mMainAdapter = new MainAdapter(this, mMainItemBeanList);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        rv_main.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        rv_main.setHasFixedSize(true);
        rv_main.setAdapter(mMainAdapter);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://yocn.com.collection/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://yocn.com.collection/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
