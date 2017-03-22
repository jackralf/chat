package com.machenfei.chat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.machenfei.chat.R;
import com.machenfei.chat.model.UserManager;
import com.machenfei.chat.model.ChatMessage;
import com.machenfei.chat.model.User;

import java.util.List;

/**
 * Created by machenfei on 2017/3/22.
 */

public class ChatPanelAdapter extends RecyclerView.Adapter<ChatPanelAdapter.ViewHolder> {

    public static final String TAG = ChatPanelAdapter.class.getSimpleName();
    private List<ChatMessage> mDataSouce;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView headImage;
        TextView msgContent;
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            headImage = (ImageView) itemView.findViewById(R.id.item_head_image);
            msgContent = (TextView) itemView.findViewById(R.id.item_message_text);
            userName = (TextView) itemView.findViewById(R.id.item_user_name);
        }
    }

    public ChatPanelAdapter(List<ChatMessage> data) {
        mDataSouce = data;
    }

    @Override
    public ChatPanelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ChatMessage.SEND_TYPE_SELF) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message_right, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message_left, parent, false);
        }
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatPanelAdapter.ViewHolder holder, int position) {
        ChatMessage msg = mDataSouce.get(position);
        Log.d(TAG, "onBindViewHolder: " + msg);
        User user = UserManager.getInstance().getUser(msg.uid);
        holder.userName.setText(user.name);
        holder.msgContent.setText(msg.content);
        holder.headImage.setImageResource(R.drawable.ic_default_avator);
    }

    @Override
    public int getItemCount() {
        return mDataSouce.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage msg = mDataSouce.get(position);
        return msg.sendType;
    }
}
