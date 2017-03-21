package com.machenfei.chat.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.machenfei.chat.R;
import com.machenfei.chat.model.ExploreItem;

import java.util.List;

/**
 * Created by machenfei on 2017/3/21.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    List<ExploreItem> mDataSouce;
    Context mContext;

    private class VIEW_TYPE {
        public static final int NORMAL = 0;
        public static final int HEADER = 1;
        public static final int FOOTER = 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView exploreItemIcon;
        TextView exploreItemName;
        int type;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            this.type = type;
            if (type == VIEW_TYPE.NORMAL) {
                exploreItemIcon = (ImageView) itemView.findViewById(R.id.explore_item_icon);
                exploreItemName = (TextView) itemView.findViewById(R.id.explore_item_name);
            }
        }
    }

    public ExploreAdapter(Context context, List<ExploreItem> data) {
        mContext = context;
        mDataSouce = data;
    }

    @Override
    public ExploreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE.NORMAL) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore, parent, false);
        } else if (viewType == VIEW_TYPE.HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_header, parent, false);
        } else if (viewType == VIEW_TYPE.FOOTER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_header, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(view, viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExploreAdapter.ViewHolder holder, int position) {
        ExploreItem item = mDataSouce.get(position);
        if (!item.isHeader() && !item.isFooter()) {
            holder.exploreItemIcon.setImageResource(item.iconId);
            holder.exploreItemName.setText(item.nameId);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSouce.size();
    }

    @Override
    public int getItemViewType(int position) {
        ExploreItem item = mDataSouce.get(position);
        if (item.isHeader()) {
            return VIEW_TYPE.HEADER;
        } else if (item.isFooter()) {
            return VIEW_TYPE.FOOTER;
        } else {
            return VIEW_TYPE.NORMAL;
        }
    }
}
