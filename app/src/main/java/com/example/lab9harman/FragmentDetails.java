package com.example.lab9harman;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentDetails extends Fragment {

    private List<ChatMessage> messageList;
    private AdapterMessage adapter;
    private int selectedMessageIndex = -1; // To track the selected message

    public FragmentDetails() {
        super(R.layout.details_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize message list with AI chatbot conversation
        messageList = new ArrayList<>();
        messageList.add(new ChatMessage("Welcome! I am your friendly AI assistant. How can I help today?"));
        messageList.add(new ChatMessage("Did you know? The fastest-growing tree is the Moringa!"));
        messageList.add(new ChatMessage("By the way, I love a good riddle. Want to hear one?"));
        messageList.add(new ChatMessage("Oh, and I can tell jokes too. Ready? Why don't skeletons fight each other? They don't have the guts!"));
        messageList.add(new ChatMessage("Alright, let me know if you need anything. I'm here 24/7!"));


        // Set up the adapter
        adapter = new AdapterMessage(messageList, position -> {
            selectedMessageIndex = position; // Track selected message
        });
        recyclerView.setAdapter(adapter);
    }

    // Method to delete the selected message
    public void deleteSelectedMessage() {
        if (selectedMessageIndex >= 0 && selectedMessageIndex < messageList.size()) {
            messageList.remove(selectedMessageIndex);
            adapter.notifyItemRemoved(selectedMessageIndex);
            selectedMessageIndex = -1; // Reset selection
        } else {
            // No message selected
            Toast.makeText(getContext(), "No message selected", Toast.LENGTH_SHORT).show();
        }
    }
}