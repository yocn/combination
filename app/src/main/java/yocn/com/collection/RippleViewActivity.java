package yocn.com.collection;

import android.app.Activity;
import android.os.Bundle;

public class RippleViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(R.layout.activity_ripple_view, null));
    }

}
