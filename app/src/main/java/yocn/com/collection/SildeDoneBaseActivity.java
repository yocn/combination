package yocn.com.collection;

import android.support.annotation.LayoutRes;
import android.view.View;

import yocn.com.collection.view.SlideRightOutView;

/**
 * Created by Yocn on 2015/12/2 0002.
 */
public class SildeDoneBaseActivity extends BaseActivity implements SlideRightOutView.SlideDownListener {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        SlideRightOutView leftSliderLayout = (SlideRightOutView) findViewById(R.id.slideRightOutView);
        leftSliderLayout.setOnSlideDownListener(this);
    }

    @Override
    public void slideDone() {
        this.finish();
    }
}
