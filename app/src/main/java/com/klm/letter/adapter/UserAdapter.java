package com.klm.letter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.klm.letter.R;
import com.klm.letter.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        public TextView textName;

        UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            textName = itemView.findViewById(R.id.text_name);
        }
    }

    private Context context;
    private ArrayList<User> users;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = users.get(position);
        holder.textName.setText(currentUser.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
