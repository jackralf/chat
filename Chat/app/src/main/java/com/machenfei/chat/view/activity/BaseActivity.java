package com.machenfei.chat.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.machenfei.chat.view.fragment.AccountFragment;
import com.machenfei.chat.view.fragment.AddressFragment;
import com.machenfei.chat.view.fragment.ChatMainFragment;
import com.machenfei.chat.R;
import com.machenfei.chat.view.fragment.ExploreFragment;

/**
 * Created by machenfei on 2017/3/19.
 */

public class BaseActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    public static final String TAG = BaseActivity.class.getSimpleName();
    Toolbar toolbar = null;
    BottomNavigationBar bottomNavigationBar;
    protected Class <?> fragmentClass;
    Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + this.getClass().getSimpleName());

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);

        initToolbar();
        initBottomNavigationBar();
        showFragment();
    }

    private void showFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.fragmentContainer, Fragment.instantiate(this, fragmentClass.getName(), this.bundle));
        ft.commitAllowingStateLoss();
    }

    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setActiveColor("#09BA00").setBarBackgroundColor("#FBFBFB");
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_messenger, getResources().getString(R.string.text_wx)))
                .addItem(new BottomNavigationItem(R.drawable.ic_address, getResources().getString(R.string.text_address)))
                .addItem(new BottomNavigationItem(R.drawable.ic_explore, getResources().getString(R.string.text_explore)))
                .addItem(new BottomNavigationItem(R.drawable.ic_person, getResources().getString(R.string.text_me)))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void hideBottomNavigationBar() {
        if (bottomNavigationBar != null) {
            bottomNavigationBar.setVisibility(View.GONE);
        }
    }

    protected void setReturnBtn() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + this.getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + this.getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this.getClass().getSimpleName());
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected: " + position);
        switch (position) {
            case 0:
                fragmentClass = ChatMainFragment.class;
                showFragment();
                break;
            case 1:
                fragmentClass = AddressFragment.class;
                showFragment();
                break;
            case 2:
                fragmentClass = ExploreFragment.class;
                showFragment();
                break;
            case 3:
                fragmentClass = AccountFragment.class;
                showFragment();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected: " + position);
    }

    @Override
    public void onTabReselected(int position) {
        Log.d(TAG, "onTabReselected: " + position);
    }
}
