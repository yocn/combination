package yocn.com.collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.Random;

import yocn.com.collection.adapter.GradientAdapter;
import yocn.com.collection.utils.DisplayUtils;

public class RecycleViewAct extends SildeDoneBaseActivity {
    private View view;
    ListView lv_gradient;
    private int mScreenWidth;
    private LinkedList<String> mStringList = new LinkedList<String>();
    Random random;
    GradientAdapter adapter;
    MyHandler myHandler;
    int num = 0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = getLayoutInflater().inflate(R.layout.activity_recycle_view, null);
        setContentView(view);
        init(view);
        myHandler = new MyHandler();
        random = new Random(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    num++;
                    int ran = random.nextInt();
                    mStringList.add("" + num);
                    myHandler.sendEmptyMessage(0);
                    if (mStringList.size() > 7) {
                        mStringList.removeFirst();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (num > 10) {
                        return;
                    }
                }
            }
        }).start();
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            adapter.setData(mStringList);
        }
    }

    public void init(View view) {
        mScreenWidth = DisplayUtils.getScreenWidth(this);
        lv_gradient = (ListView) view.findViewById(R.id.lv_gradient);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) lv_gradient.getLayoutParams();
        param.width = 3 * mScreenWidth / 4;
        lv_gradient.setLayoutParams(param);
        adapter = new GradientAdapter(this, mStringList);
        lv_gradient.setAdapter(adapter);

    }

    /**
     *
     * @param v
     * @param al
     */
    private void collapse(final View v, Animation.AnimationListener al) {
        final int initialHeight = v.getMeasuredHeight();

        Animation anim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        if (al != null) {
            anim.setAnimationListener(al);
        }
        anim.setDuration(1000);
        v.startAnimation(anim);
    }

}
