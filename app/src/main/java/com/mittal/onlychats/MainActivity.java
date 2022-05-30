package com.mittal.onlychats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i=new Intent(MainActivity.this,signup.class);
//                startActivity(i);
//                finish();
//            }
//        },1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if (currentUser!=null){

            Intent i=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(i);
        }
        else
        {
            Intent i=new Intent(MainActivity.this,signup.class);
            startActivity(i);
        }
    }
}