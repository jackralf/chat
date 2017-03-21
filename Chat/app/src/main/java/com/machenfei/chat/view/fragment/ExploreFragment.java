package com.machenfei.chat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.machenfei.chat.R;
import com.machenfei.chat.model.ExploreItem;
import com.machenfei.chat.view.adapter.ExploreAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machenfei on 2017/3/21.
 */

public class ExploreFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ExploreItem> mData = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        int[] nameIds = new int[]{R.string.text_explore_1, R.string.text_explore_2, R.string.text_explore_3, R.string.text_explore_4,R.string.text_explore_5, R.string.text_explore_6, R.string.text_explore_7};
        int[] iconIds = new int[]{R.drawable.orange};

        for (int i = 0; i < nameIds.length; i ++) {
            ExploreItem header = new ExploreItem();
            header.setHeader(true);
            mData.add(header);
            ExploreItem item = new ExploreItem();
            item.nameId = nameIds[i];
            item.iconId = R.drawable.orange;
            mData.add(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new ExploreAdapter(getContext(), mData);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
