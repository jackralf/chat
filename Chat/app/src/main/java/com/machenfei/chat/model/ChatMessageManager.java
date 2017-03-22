package com.machenfei.chat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machenfei on 2017/3/22.
 */

public class ChatMessageManager {
    List<ChatMessage> mList = new ArrayList<>();

    private static ChatMessageManager manager;
    public static ChatMessageManager getInstance() {
        if (manager == null) {
            manager = new ChatMessageManager();
            manager.init();
        }
        return manager;
    }

    private void init() {
        for (int i = 0; i < 10; i ++) {
            ChatMessage msg = new ChatMessage();
            msg.content = "hello world hello worldhello worldhello worldhello worldhello world";
            msg.sendType = i % 2 == 0 ? ChatMessage.SEND_TYPE_SELF : ChatMessage.SEND_TYPE_OTHER;
            msg.msgType = 1;
            mList.add(msg);
        }
    }

    public List<ChatMessage> getData() {
        return mList;
    }

    public void addChatMessage(ChatMessage msg) {
        mList.add(msg);
    }
}
