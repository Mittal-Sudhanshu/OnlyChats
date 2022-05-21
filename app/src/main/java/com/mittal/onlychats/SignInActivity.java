package com.mittal.onlychats;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mittal.onlychats.databinding.ActivitySignInBinding;
import com.mittal.onlychats.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        dialog=new ProgressDialog(SignInActivity.this);
        dialog.setTitle("Login");
        dialog.setMessage("Validation in Progress");
        binding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.emai1.getText().toString().isEmpty()&&!binding.pass.getText().toString().isEmpty()){
                    dialog.show();
                    mAuth.signInWithEmailAndPassword(binding.emai1.getText().toString(),binding.pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    dialog.dismiss();
                                    if (task.isSuccessful()){
                                        Intent i=new Intent(SignInActivity.this,HomeActivity.class);
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(SignInActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(SignInActivity.this,"Please enter the required Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.createna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignInActivity.this,signup.class);
                startActivity(i);
            }
        });

    }
}