package yocn.com.collection.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import yocn.com.collection.R;

public class SlideRightOutView extends DetectsSoftKeyboardRelativeLayout {

    private Context mContext;
    private View mainView;
    // private View bgShadeView;
    private int screenWidth;
    
    private int mFirstViewPagerPosition = 0;
    private int mSecondViewPagerPosition = 0;

    public SlideRightOutView(Context context) {
        super(context);
        init(context);
    }

    public SlideRightOutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideRightOutView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mTouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
        mScroller = new Scroller(mContext);
        if (mContext instanceof Activity) {
            screenWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        }
        this.postDelayed(new Runnable() {

            @Override
            public void run() {

                mainView = (View) findViewById(R.string.main_content);

            }
        }, 100);
    }
    
    public void setFirstViewPagerPosition(int position) {
    	mFirstViewPagerPosition = position;
    }
    
    public void setSecondViewPagerPosition(int position) {
    	mSecondViewPagerPosition = position;
    }

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private float mLastMotionX;
    private float mLastMotionY;
    private static final int VELOCITY = 50;
    private boolean mIsBeingDragged = false;
    private boolean canSlide = true;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (canSlide) {
            final int action = ev.getAction();
            final float x = ev.getX();
            final float y = ev.getY();
            switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
                mIsBeingDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final float dx = x - mLastMotionX;
                final float xDiff = Math.abs(dx);
                final float yDiff = Math.abs(y - mLastMotionY);
                if (xDiff > mTouchSlop) {
                    if (xDiff > yDiff && (mFirstViewPagerPosition ==0 || mSecondViewPagerPosition == 0)) {
                        mIsBeingDragged = true;
                        isFirst = true;
                    }
                }
                // if(xDiff <= mTouchSlop){
                // ev.setLocation(mLastMotionX, mLastMotionY);
                // }
                break;
            }
            if (mIsBeingDragged)
                return mIsBeingDragged;
            else
                return super.onInterceptTouchEvent(ev);
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);

        final int action = ev.getAction();
        final float x = ev.getX();
        final float y = ev.getY();

        if (isFirst) {
            isFirst = false;
            if (mSlideListener != null)
                mSlideListener.slideStart();
        }

        switch (action) {
        case MotionEvent.ACTION_DOWN:
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
            mLastMotionX = x;
            mLastMotionY = y;

            break;
        case MotionEvent.ACTION_MOVE:

            if (mainView == null)
                break;

            // if (mIsBeingDragged) {
            {
                final float deltaX = mLastMotionX - x;
                mLastMotionX = x;
                float oldScrollX = mainView.getScrollX();
                float scrollX = oldScrollX + deltaX;
                if (scrollX > 0) {
                    scrollX = 0;
                }
                if (deltaX < 0 && oldScrollX < 0) { // left view
                    final float leftBound = 0;
                    final float rightBound = -screenWidth;
                    if (scrollX > leftBound) {
                        scrollX = leftBound;
                    } else if (scrollX < rightBound) {
                        scrollX = rightBound;
                    }
                }
                mainView.scrollTo((int) scrollX, mainView.getScrollY());
                changeAlpha();

            }
            break;
        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP:
            if (mainView == null)
                break;

            if (mSlideListener != null)
                mSlideListener.slideEnd();

            // if (mIsBeingDragged) {
            {
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(100);
                float xVelocity = velocityTracker.getXVelocity();
                int oldScrollX = mainView.getScrollX();
                int dx = 0;

                if (oldScrollX <= 0) {// left view
                    if (xVelocity > VELOCITY) {
                        dx = -screenWidth - oldScrollX;
                    } else if (xVelocity < -VELOCITY) {
                        dx = -oldScrollX;
                    } else if (oldScrollX < -screenWidth / 2) {
                        dx = -screenWidth - oldScrollX;
                    } else if (oldScrollX >= -screenWidth / 2) {
                        dx = -oldScrollX;
                    }

                }
                if (oldScrollX > 0) {
                    dx = -oldScrollX;
                }
                smoothScrollTo(dx);
            }
            break;
        }
        return true;
    }

    private void changeAlpha() {

        if (mainView == null)
            return;

        Drawable d = mainView.getBackground();
        double deltaPha = (screenWidth + mainView.getScrollX() + 0.0) / screenWidth;
        double alpha = 225 * deltaPha;
        d.setAlpha((int) alpha);
        mainView.setBackgroundDrawable(d);
    }

    private void smoothScrollTo(int dx) {

        if (mainView == null)
            return;

        int duration = 500;
        int oldScrollX = mainView.getScrollX();
        mScroller.startScroll(oldScrollX, mainView.getScrollY(), dx, mainView.getScrollY(), duration);
        invalidate();
    }

    @Override
    public void computeScroll() {

        if (mainView == null)
            return;

        if (!mScroller.isFinished()) {
            if (mScroller.computeScrollOffset()) {
                int oldX = mainView.getScrollX();
                int oldY = mainView.getScrollY();
                int x = mScroller.getCurrX();
                int y = mScroller.getCurrY();
                if (oldX != x || oldY != y) {
                    mainView.scrollTo(x, y);
                    changeAlpha();
                    if (mainView.getScrollX() < -screenWidth + 10) {
                        finish();
                    }
                    // Logger.log("mainView.getScrollX()" +
                    // mainView.getScrollX());
                }
                postInvalidate();
            }
        }
    }

    private boolean isFinish = false;

    private void finish() {

        if (isFinish)
            return;

        isFinish = true;

        if (mainView != null)
            mainView.setVisibility(View.GONE);

        if (mContext == null)
            return;

        // if (mContext instanceof Activity) {
        // if (!((Activity) mContext).isFinishing()) {
        // ((Activity) mContext).finish();
        // }
        // } else {
        if (mSlideDownListener != null) {
            mSlideDownListener.slideDone();
        }
        // }

    }

    private boolean isFirst = true;
    private SlideListener mSlideListener;
    private SlideDownListener mSlideDownListener;

    public void setOnSlideListener(SlideListener l) {

        mSlideListener = l;

    }

    public void setOnSlideDownListener(SlideDownListener l) {

        mSlideDownListener = l;

    }

    public interface SlideListener {
        public void slideStart();

        public void slideEnd();
    }

    public interface SlideDownListener {
        public void slideDone();
    }

}
