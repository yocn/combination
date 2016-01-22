package yocn.com.collection.utils;

import android.content.Context;

/**
 * Created by JD on 2016/1/22 0022.
 */
public class Utils {

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
