package yocn.com.collection.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.ObjectAnimator;

import yocn.com.collection.R;

/**
 * Created by JD on 2016/1/25 0025.
 */
public class UpView extends LinearLayout {
    private Paint mPaint;
    private ObjectAnimator objectAnimator;
    private Context mContext;
    Animation animation;
    View view;


    public UpView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init(context);
        LayoutInflater.from(context).inflate(R.layout.item_up_view_type_chat, this, true);
        animation = AnimationUtils.loadAnimation(context, R.anim.gradually);
        this.startAnimation(animation);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mContext = context;
        mPaint.setAlpha(100);
        mPaint.setColor(Color.GREEN);
        mPaint.setColor(Color.rgb(27, 157, 255));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_up_view_type_chat, null);
        animation = AnimationUtils.loadAnimation(context, R.anim.gradually);
        this.startAnimation(animation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("onDraw");
//        canvas.
//        canvas.drawPath(path2, mPaint);// 画出贝塞尔曲线
    }
}
