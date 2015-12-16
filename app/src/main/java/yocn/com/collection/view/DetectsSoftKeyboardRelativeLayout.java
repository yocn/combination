package yocn.com.collection.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Window;
import android.widget.RelativeLayout;

import yocn.com.collection.utils.DisplayUtils;

public class DetectsSoftKeyboardRelativeLayout extends RelativeLayout {

    public DetectsSoftKeyboardRelativeLayout(Context context) {
        super(context);
    }

    public DetectsSoftKeyboardRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetectsSoftKeyboardRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getContext() instanceof Activity) {
            int height = MeasureSpec.getSize(heightMeasureSpec);
            Activity activity = (Activity) getContext();
            Rect rect = new Rect();
            Window wd = activity.getWindow();
            wd.getDecorView().getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;
            int screenHeight = DisplayUtils.getScreenHeight(getContext());
            int diff = (screenHeight - statusBarHeight) - height;
            boolean isShowing = diff > 128;
            if (listener != null) {
                listener.onSoftKeyboardShown(isShowing); // 一般软键盘高度至少大于128px
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public interface Listener {
        public void onSoftKeyboardShown(boolean isShowing);
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
