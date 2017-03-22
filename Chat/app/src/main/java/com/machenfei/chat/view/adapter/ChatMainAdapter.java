package com.machenfei.chat.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.machenfei.chat.R;
import com.machenfei.chat.model.ChatMainItem;
import com.machenfei.chat.view.activity.ChatPanelActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by machenfei on 2017/3/14.
 */

public class ChatMainAdapter extends RecyclerView.Adapter<ChatMainAdapter.ViewHolder> {

    public static final String TAG = ChatMainAdapter.class.getSimpleName();
    private Context mContext;
    private List<ChatMainItem> dataSource;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            textView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    public ChatMainAdapter(Context context, List<ChatMainItem> data) {
        mContext = context;
        dataSource = data;
    }

    @Override
    public ChatMainAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ChatPanelActivity.class);
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatMainAdapter.ViewHolder holder, int position) {

        ChatMainItem item = dataSource.get(position);
        Log.d(TAG, "onBindViewHolder: " + item.url);
        Picasso.with(mContext).load(item.url).resize(300,300).centerCrop().into(holder.imageView);
        holder.textView.setText(item.who);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
