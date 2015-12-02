package yocn.com.collection.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import yocn.com.collection.R;
import yocn.com.collection.utils.DisplayUtils;

/**
 * 此view是横向柱状图的view，实现以下两点需求：
 * <p/>
 * 1、横向的长度表示大小
 * <p/>
 * 2、横向的越长颜色越深，越短颜色越浅
 *
 * @author YocnZhao
 */
public class BarChartView extends View {

    /**
     * 柱状条的高度
     */
    private int chartHeight = 1;
    /**
     * 柱状条的长度
     */
    private int chartWidth = 255;
    /**
     * 柱状条的颜色值
     */
    private int chartColor = 0xff0000ff;

    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, int width) {
        super(context);
        chartWidth = width;
        chartColor = Color.argb(255, 255, 255, 0);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BarChartView);
        chartHeight = a.getInt(R.styleable.BarChartView_bar_height, 20);
        chartWidth = a.getResourceId(R.styleable.BarChartView_bar_width, 10);
        a.recycle();
        System.out.println("chartHeight--" + chartHeight);
        System.out.println("chartWidth--" + chartWidth);
        chartHeight = DisplayUtils.Dp2Px(context, chartHeight);
        System.out.println("chartHeight--" + DisplayUtils.Dp2Px(context, chartHeight));
        System.out.println("chartWidth--" + DisplayUtils.Dp2Px(context, chartHeight));

    }

    /**
     * 改变view的颜色值
     *
     * @param color 0~255之间，越多表示红色越多
     */
    public void setColor(int color) {
        chartWidth = color;
        /** 创建一个color并获得给定的RGB的int值 */
        chartColor = Color.argb(255, 255, 255 - color, 0);
    }

    public void setHeight(int height) {
        if (chartHeight != 0) {
            chartHeight = height;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(chartColor);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);

        Bitmap b = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_4444);

        // canvas.drawCircle(50, x1, 10, p);
        canvas.drawRect(0, 0, chartWidth, chartHeight, p);
        super.onDraw(canvas);
    }

}
