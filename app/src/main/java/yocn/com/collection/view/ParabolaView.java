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
    /**
     * 手指按下去时候的Y坐标
     */
    private float downBaseY;//向下滑动的时候，作为基准的Y坐标
    private float upBaseY;//向上滑动的时候，作为基准的Y坐标
    private float deltaY;
    private float y;
    private float x;
    float paddingY;
    private float limitY = 400;
    /**
     * 判断是上拉还是下拉
     */
    private boolean isDownOrUp = false;
    private float previousY;//存储之前的Y坐标，判断是向上滑动还是向下滑动

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
//            Logger.d("---" + event.getOrientation());
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
        previousY = downBaseY = event.getY();
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

    /**
     * baseY:手指按下去时候的Y坐标
     * deltaY:抛物线的高度，同贝塞尔曲线的顶点高度
     * limitY:抛物线最大限制高度
     *
     * @param event
     */
    public void actionMove(MotionEvent event) {
        x = (x < mWidth / 4) ? mWidth / 4 : x;
        x = (x > 3 * mWidth / 4) ? mWidth * 3 / 4 : x;
        y = event.getY();
        x = event.getX();
        if (y - previousY > 0) {
            /**方向向下*/
            isDownOrUp = true;
        }
        if (previousY - y > 0) {
            /**方向向上,*/
            isDownOrUp = false;
        }
        deltaY = y - downBaseY;

        /**
         * 设置两种状态 ，方向向下和向上，向下的时候deltaY大于400的时候一直400，
         * 向上的时候
         */
        if (isDownOrUp) {
            /**↓↓↓↓↓↓向下滑动的时候,deltaY变大*/
            if (deltaY < limitY) {
                /**deltaY < limitY*/
            } else {
                /**deltaY > limitY*/
                deltaY = limitY;
            }
        } else {
            /**↑↑↑↑↑↑向上滑动的时候，deltaY变小*/
            Logger.d("deltaY--" + deltaY);
            Logger.d("upBaseY--" + upBaseY);
            Logger.d("y--" + y);
//            deltaY = deltaY - (upBaseY - y);
        }

        paddingY = limitY - deltaY;
        path2.reset();
        path2.moveTo(0, paddingY);// 设置Path的起点,跟listview配合，listview设置了ParabolaView的padding，需要跟随listview发生变化
        path2.quadTo(x, limitY, mWidth, paddingY); // 设置贝塞尔曲线的控制点坐标和终点坐标
        invalidate();
        //设置preY，检测手指向上还是向下滑动
        previousY = event.getY();
        if(!isDownOrUp){
            upBaseY = y;
        }
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
