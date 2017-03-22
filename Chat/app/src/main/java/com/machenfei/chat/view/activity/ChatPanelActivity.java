package com.machenfei.chat.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.machenfei.chat.view.fragment.ChatPanelFragment;

/**
 * Created by machenfei on 2017/3/21.
 */

public class ChatPanelActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentClass = ChatPanelFragment.class;
        super.onCreate(savedInstanceState);
        hideBottomNavigationBar();
        setReturnBtn();
    }
}
