package com.mittal.onlychats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mittal.onlychats.databinding.ActivityChatScreenBinding;

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
        String senderId=auth.getUid();
        String recieverId=getIntent().getStringExtra("userId");
        String name=getIntent().getStringExtra("name");
        String profilePic=getIntent().getStringExtra("profilePic");
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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