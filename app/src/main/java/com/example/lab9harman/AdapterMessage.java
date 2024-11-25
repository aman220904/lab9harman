package com.example.lab9harman;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.MessageViewHolder> {

    private final List<ChatMessage> messages;
    private final OnMessageClickListener listener;

    public AdapterMessage(List<ChatMessage> messages, OnMessageClickListener listener) {
        this.messages = messages;
        this.listener = listener;
    }

    public interface OnMessageClickListener {
        void onMessageClick(int position);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.textView.setText(message.getText());

        // Set click listener for each message
        holder.itemView.setOnClickListener(v -> listener.onMessageClick(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
