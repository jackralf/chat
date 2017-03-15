package com.machenfei.chat;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machenfei on 2017/3/13.
 */

public class ChatMainFragment extends Fragment{
    public static final String TAG = ChatMainFragment.class.getSimpleName();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<ChatMainItem> data = new ArrayList<ChatMainItem>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat_main, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_widget);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(true, 0, 50);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "onRefresh", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });


        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

//        layoutManager = new LinearLayoutManager(getContext());
        layoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ChatMainAdapter(data);
        recyclerView.setAdapter(adapter);

        return v;
    }

    String[] name = new String[] {"apple", "banana", "orange"};
    int[] image = new int[] {R.drawable.apple, R.drawable.banana, R.drawable.orange};
    private void init() {
        for (int i = 0; i < 50; i ++) {
            ChatMainItem item = new ChatMainItem();
            int idx = (int)(Math.random() * name.length);
            item.iconImageId = image[idx];
            item.name = name[idx];
            data.add(item);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, msg.toString());
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
}
