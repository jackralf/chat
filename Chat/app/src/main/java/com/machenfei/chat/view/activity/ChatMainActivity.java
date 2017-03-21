package com.machenfei.chat.view.activity;

import android.os.Bundle;

import com.machenfei.chat.R;
import com.machenfei.chat.view.activity.BaseActivity;
import com.machenfei.chat.view.fragment.ChatMainFragment;

public class ChatMainActivity extends BaseActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentClass = ChatMainFragment.class;
        super.onCreate(savedInstanceState);
        setTitle(R.string.text_wx);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
