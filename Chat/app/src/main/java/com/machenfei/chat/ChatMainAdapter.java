package com.machenfei.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by machenfei on 2017/3/14.
 */

public class ChatMainAdapter extends RecyclerView.Adapter<ChatMainAdapter.ViewHolder> {

    private List<ChatMainItem> dataSource;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.chat_main_icon);
            textView = (TextView) itemView.findViewById(R.id.chat_main_name);
        }
    }

    ChatMainAdapter(List<ChatMainItem> data) {
        dataSource = data;
    }

    @Override
    public ChatMainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatMainAdapter.ViewHolder holder, int position) {
        ChatMainItem item = dataSource.get(position);
        holder.imageView.setImageResource(item.iconImageId);
        holder.textView.setText(item.name);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
