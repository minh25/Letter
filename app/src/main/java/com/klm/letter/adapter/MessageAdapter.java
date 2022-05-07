package com.klm.letter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.klm.letter.R;
import com.klm.letter.model.Message;

import java.util.ArrayList;
import java.util.Objects;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Message> messages;
    private int ITEM_SENT = 1;
    private int ITEM_RECEIVED = 2;


    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.send, parent, false);
            return new SentMessageViewHolder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.received, parent, false);
        return new ReceivedMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Message currentMessage = messages.get(position);

        if (holder instanceof SentMessageViewHolder)
        {
            ((SentMessageViewHolder) holder).textName.setText(currentMessage.getMessage());
        }
        else if (holder instanceof ReceivedMessageViewHolder)
        {
            ((ReceivedMessageViewHolder) holder).textName.setText(currentMessage.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {

        Message currentMessage = messages.get(position);

        if (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid().equals(currentMessage.getSenderID()))
            return ITEM_SENT;
        return ITEM_RECEIVED;
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        public TextView textName;

        SentMessageViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            textName = itemView.findViewById(R.id.text_sent_message);
        }
    }
    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        public TextView textName;

        ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            textName = itemView.findViewById(R.id.text_received_message);
        }
    }
}
