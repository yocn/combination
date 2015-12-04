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
    private float x;
    private float mWidth;
    private float baseY;
    private float deltaY;
    private float y;
    private float limitY = 600;
    private float mBaseHeight;

    public ParabolaView(Context context) {
        super(context);
    }

    public ParabolaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ParabolaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mWidth = DisplayUtils.getScreenWidth(context);
        mBaseHeight = DisplayUtils.getScreenHeight(context) / 2;
        p.setColor(Color.BLUE);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);
        path2.moveTo(0, 0);// 设置Path的起点
        path2.quadTo(mWidth / 2, 0, mWidth, 0); // 设置贝塞尔曲线的控制点坐标和终点坐标
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                baseY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                Logger.d("ACTION_UP----" + y);
                while (deltaY >= 0) {
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                    }
                    deltaY = deltaY - 13;
                    Logger.d("yy----" + deltaY);
                    path2.reset();
                    path2.moveTo(0, 0);// 设置Path的起点
                    path2.quadTo(mWidth / 2, deltaY, mWidth, 0); // 设置贝塞尔曲线的控制点坐标和终点坐标
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                y = event.getY();
                deltaY = y - baseY;
                deltaY = deltaY < limitY ? deltaY : limitY;
                path2.reset();
                path2.moveTo(0, 0);// 设置Path的起点
                path2.quadTo(mWidth / 2, deltaY, mWidth, 0); // 设置贝塞尔曲线的控制点坐标和终点坐标
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.d("onDraw");
        p.setColor(Color.BLUE);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);
        canvas.drawPath(path2, p);// 画出贝塞尔曲线
    }


}
