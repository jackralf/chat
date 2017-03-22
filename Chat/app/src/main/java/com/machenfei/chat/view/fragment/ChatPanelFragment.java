package com.machenfei.chat.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.machenfei.chat.R;
import com.machenfei.chat.model.ChatMessage;
import com.machenfei.chat.model.ChatMessageManager;
import com.machenfei.chat.view.adapter.ChatPanelAdapter;

import java.util.List;

/**
 * Created by machenfei on 2017/3/21.
 */

public class ChatPanelFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private EditText editText;
    private CheckBox checkBox;
    private Button btnEmoji;
    private Button btnAdd;
    private Button btnSend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_panel, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<ChatMessage> data = ChatMessageManager.getInstance().getData();
        adapter = new ChatPanelAdapter(data);
        recyclerView.setAdapter(adapter);

        editText = (EditText) view.findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSend.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                btnAdd.setVisibility(s.length() == 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        checkBox = (CheckBox) view.findViewById(R.id.edit_checkbox);
        btnEmoji = (Button) view.findViewById(R.id.btn_emoji);
        btnAdd = (Button) view.findViewById(R.id.btn_add);
        btnSend = (Button) view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text.length() > 0) {
                    ChatMessage msg = new ChatMessage();
                    msg.content = text;
                    msg.sendType = ChatMessage.SEND_TYPE_SELF;
                    msg.uid = "123";
                    ChatMessageManager.getInstance().addChatMessage(msg);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(ChatMessageManager.getInstance().getData().size() - 1);
                    editText.getText().clear();
                }
            }
        });

        return view;
    }
}
