package com.klm.letter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.klm.letter.R;
import com.klm.letter.adapter.MessageAdapter;
import com.klm.letter.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chat extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private EditText editMessage;
    private ImageView sendButton;
    private MessageAdapter messageAdapter;
    private ArrayList<Message> messages;

    private DatabaseReference databaseReference;

    private String receiveRoom;
    private String sendRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String name = getIntent().getStringExtra("name");
        String receiverUid = getIntent().getStringExtra("uid");

        String senderUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        sendRoom = receiverUid + senderUid;
        receiveRoom = senderUid + receiverUid;

        Objects.requireNonNull(getSupportActionBar()).setTitle(name);

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        editMessage = findViewById(R.id.messageBox);
        sendButton = findViewById(R.id.sendButton);
        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messages);

        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(messageAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("chats").child(sendRoom).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    messages.add(message);
                }

                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendButton.setOnClickListener(v -> {
            String message = editMessage.getText().toString();
            Message messageObj = new Message(message, senderUid);

            databaseReference.child("chats").child(sendRoom).child("messages").push().setValue(messageObj)
                    .addOnSuccessListener(unused -> databaseReference.child("chats").child(receiveRoom).child("messages").push().setValue(messageObj));

            editMessage.setText("");
        });
    }
}