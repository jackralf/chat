package com.machenfei.chat.view.fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.machenfei.chat.R;
import com.machenfei.chat.model.ChatMainItem;
import com.machenfei.chat.view.DividerItemDecoration;
import com.machenfei.chat.view.adapter.ChatMainAdapter;

import org.json.JSONException;
import org.json.JSONObject;

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
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String results = jsonObject.getString("results");
                    Gson gson = new Gson();
                    List<ChatMainItem> list = gson.fromJson(results, new TypeToken<List<ChatMainItem>>() {}.getType());
                    data.addAll(list);
                    if (adapter != null) {
                        Log.d(TAG, "onResponse: not null");
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        });

        requestQueue.add(request);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
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

        layoutManager = new LinearLayoutManager(getContext());
//        layoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
//        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getContext(),
                DividerItemDecoration.VERTICAL_LIST,
                new ColorDrawable(getResources().getColor(R.color.colorItemDivider)));
        dividerItemDecoration.setHeight(2);
        recyclerView.addItemDecoration(dividerItemDecoration);

        Log.d(TAG, "data size:" + data.size());
        adapter = new ChatMainAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);

        return v;
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
