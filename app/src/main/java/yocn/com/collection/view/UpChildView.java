package yocn.com.collection.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

import yocn.com.collection.utils.DisplayUtils;

/**
 * Created by JD on 2016/1/25 0025.
 * 引入一个布局，这个布局只能存在3秒，3秒之后会淡出
 */
public class UpChildView extends View {
    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private int mScreenWidth;
    Context mContext;
    private int VIEW_TYPE;
    public final static int VIEW_TYPE_CHAT = 1000;
    public final static int VIEW_TYPE_GIFT = 1001;

    public UpChildView(Context context) {
        super(context);
        init(context);
    }

    public UpChildView(Context context, int type) {
        super(context);
        init(context);
    }

    public UpChildView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public UpChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mScreenWidth = DisplayUtils.getScreenWidth(mContext);
        mWidth = mScreenWidth * 3 / 4;
        mPaint = new Paint();
        mPaint.setAlpha(100);
        mPaint.setColor(Color.rgb(27, 157, 255));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPath(path2, mPaint);// 画出贝塞尔曲线
//        canvas.drawBitmap();
    }
}
