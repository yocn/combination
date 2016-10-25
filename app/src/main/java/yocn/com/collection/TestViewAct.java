package yocn.com.collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.Random;

import yocn.com.collection.adapter.GradientAdapter;
import yocn.com.collection.utils.DisplayUtils;
import yocn.com.collection.view.UpView;

public class TestViewAct extends SildeDoneBaseActivity {
    private View view;
    Button bt_test;
    UpView upview;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = getLayoutInflater().inflate(R.layout.activity_test_view, null);
        setContentView(view);
        init(view);
    }


    public void init(View view) {
        upview = (UpView) findViewById(R.id.upview);
        bt_test = (Button) view.findViewById(R.id.bt_text);
        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upview.invalidate();
            }
        });
    }


}
