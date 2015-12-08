package yocn.com.collection.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import yocn.com.collection.utils.DisplayUtils;
import yocn.com.collection.utils.Logger;

public class ParabolaView extends ImageView {
    private Path path2 = new Path();
    private Paint p = new Paint();
    private float mWidth;
    private float baseY;
    private float deltaY;
    private float y;
    private float x;
    private float limitY = 400;

    public ParabolaView(Context context) {
        super(context);
    }

    public ParabolaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ParabolaView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        limitY = DisplayUtils.Dp2Px(context, 200);
        mWidth = DisplayUtils.getScreenWidth(context);
        p.setColor(Color.rgb(27, 157, 255));
        p.setAntiAlias(true);
        p.setStrokeWidth(10);
        path2.moveTo(0, 0);// 设置Path的起点
        path2.quadTo(mWidth / 2, 0, mWidth, 0); // 设置贝塞尔曲线的控制点坐标和终点坐标
        Logger.d("---" + limitY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDown(event);
                break;
            case MotionEvent.ACTION_UP:
                actionUp();
                break;
            case MotionEvent.ACTION_MOVE:
                actionMove(event);
                break;
        }
        return true;
    }

    public void actionDown(MotionEvent event) {
        baseY = event.getY();
    }

    public void actionUp() {
        while (deltaY >= 0) {
            deltaY = deltaY - 13;
            path2.reset();
            path2.moveTo(0, 0);// 设置Path的起点
            path2.quadTo(mWidth / 2, deltaY, mWidth, 0); // 设置贝塞尔曲线的控制点坐标和终点坐标
            invalidate();
        }
    }

    public void actionMove(MotionEvent event) {
        y = event.getY();
        x = event.getX();
        x = (x < mWidth / 4) ? mWidth / 4 : x;
        x = (x > 3 * mWidth / 4) ? mWidth * 3 / 4 : x;
        deltaY = y - baseY;
        Logger.d("-----" + deltaY);
        deltaY = deltaY < limitY ? deltaY : limitY;
        path2.reset();
        path2.moveTo(0, limitY - deltaY);// 设置Path的起点
        path2.quadTo(x, limitY, mWidth, limitY - deltaY); // 设置贝塞尔曲线的控制点坐标和终点坐标
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.rgb(27, 157, 255));
        p.setAntiAlias(true);
        p.setStrokeWidth(10);
        canvas.drawPath(path2, p);// 画出贝塞尔曲线
    }

}
