package yocn.com.collection.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import yocn.com.collection.R;
import yocn.com.collection.utils.Logger;

/**
 * Created by Yocn on 2015/12/7 0007.
 */
public class Pull2RefreshList extends ListView {

    private LayoutInflater mInflater;
    private Context context;
    private LinearLayout mHeaderView;
    private ParabolaView mParabolaView;
    private boolean isRefresh = false;
    private float startY;
    private float currentY;
    private float deltaY = 0;
    private int mHeadViewHeight;
    private OnFreshListener mOnFreshListener;

    public Pull2RefreshList(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public Pull2RefreshList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public Pull2RefreshList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        mInflater = LayoutInflater.from(context);
        addHeaderView();
    }

    public void setOnFreshListener(OnFreshListener mOnFreshListener) {
        this.mOnFreshListener = mOnFreshListener;
    }

    public interface OnFreshListener {
        void refresh();
    }

    /**
     * init headerView
     */
    private void addHeaderView() {
        mHeaderView = (LinearLayout) mInflater.inflate(R.layout.header_parabola, null);
        mParabolaView = (ParabolaView) mHeaderView.findViewById(R.id.pv);
        measureView(mHeaderView);
        mHeadViewHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0, -1 * mHeadViewHeight, 0, 0);
        mHeaderView.invalidate();
        addHeaderView(mHeaderView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                mParabolaView.actionDown(ev);
                break;
            case MotionEvent.ACTION_UP:
                mHeaderView.setPadding(0, (-1 * mHeadViewHeight), 0, 0);
                mParabolaView.actionUp();
                if (isRefresh) {
                    if (mOnFreshListener != null) {
                        mOnFreshListener.refresh();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = ev.getY();
                deltaY = currentY - startY;
                isRefresh = deltaY > 0 ? true : false;

                if (deltaY >= mHeadViewHeight) {
                    deltaY = mHeadViewHeight;
                }
                mHeaderView.setPadding(0, (int) (-1 * mHeadViewHeight + deltaY), 0, 0);
                mParabolaView.actionMove(ev);
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 测量HeadView宽高(注意：此方法仅适用于LinearLayout，请读者自己测试验证。)
     *
     * @param pChild
     * @date 2013-11-20 下午4:12:07
     * @change JohnWatson
     * @version 1.0
     */
    private void measureView(View pChild) {
        ViewGroup.LayoutParams p = pChild.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;

        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        pChild.measure(childWidthSpec, childHeightSpec);
    }
}
