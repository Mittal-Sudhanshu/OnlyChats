package com.mittal.onlychats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mittal.onlychats.databinding.ActivityChatScreenBinding;

import java.util.ArrayList;
import java.util.Date;

public class ChatScreen extends AppCompatActivity {
    ActivityChatScreenBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final String getName=getIntent().getStringExtra("name");
        binding.Name.setText(getName);
        getSupportActionBar().hide();
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        final String senderId=auth.getUid();
        String receiverId=getIntent().getStringExtra("userId");
        String name=getIntent().getStringExtra("name");

        final String senderRoom=senderId+receiverId;
        final String receiverRoom=receiverId+senderId;
        String profilePic=getIntent().getStringExtra("profilePic");
        final ArrayList<ChatList> chatList=new ArrayList<>();

        final ChatAdapter chatAdapter=new ChatAdapter(chatList,this);

        binding.recView.setAdapter(chatAdapter);


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        binding.recView.setLayoutManager(layoutManager);



        database.getReference().child("chats")
                        .child(senderRoom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        chatList.clear();
                                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                                            ChatList lists=snapshot.getValue(ChatList.class);
                                            chatList.add(lists);
                                        }
                                        chatAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


            binding.sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String message = binding.Message.getText().toString();
                    final ChatList list = new ChatList(senderId, message);
                    list.setTimestamp(new Date().getTime());
                    binding.Message.setText("");

                    database.getReference().child("chats")
                            .child(senderRoom)
                            .push()
                            .setValue(list).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    database.getReference().child("chats")
                                            .child(receiverRoom)
                                            .push()
                                            .setValue(list).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                }
                                            });
                                }
                            });
                    }
            });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}