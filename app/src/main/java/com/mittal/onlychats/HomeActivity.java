package com.mittal.onlychats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mittal.onlychats.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    FirebaseAuth auth;
    ArrayList<Users> list=new ArrayList<>();
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        UsersAdapter adapter=new UsersAdapter(list,HomeActivity.this);
        binding.RecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(HomeActivity.this);
        binding.RecyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Users users=dataSnapshot.getValue(Users.class);
                    users.getUserId();
                    list.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.settingsBtn:

                Toast.makeText(HomeActivity.this,"Settings Button Clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logoutBtn:
                auth.signOut();
                Intent i=new Intent(HomeActivity.this,SignInActivity.class);
                startActivity(i);
                super.finish();

                break;
        }


        return super.onOptionsItemSelected(item);
    }
}