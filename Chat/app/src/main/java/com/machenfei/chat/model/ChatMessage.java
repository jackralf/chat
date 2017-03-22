package com.machenfei.chat.model;

/**
 * Created by machenfei on 2017/3/22.
 */

public class ChatMessage {
    public static final int SEND_TYPE_SELF = 1;
    public static final int SEND_TYPE_OTHER = 2;

    public int msgType;
    public String id;
    public String content;
    public String uid;
    public int sendType;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "msgType=" + msgType +
                ", id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", uid='" + uid + '\'' +
                ", sendType=" + sendType +
                '}';
    }
}
