package com.mittal.onlychats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mittal.onlychats.databinding.ActivitySignupBinding;
import com.mittal.onlychats.databinding.ActivitySignupBinding;

public class signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        getSupportActionBar().hide();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        dialog=new ProgressDialog(signup.this);
        dialog.setTitle("Creating Account");
        dialog.setMessage("We're creating your account");
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.username.getText().toString().isEmpty()&&!binding.email.getText().toString().isEmpty()&&!binding.password.getText().toString().isEmpty()&&!binding.Cnfpassword.getText().toString().isEmpty()&&(binding.password.getText().toString().equals(binding.Cnfpassword.getText().toString()))){
                    dialog.show();
                    mAuth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    dialog.dismiss();
                                    if (task.isSuccessful()){
                                        Users user=new Users(binding.username.getText().toString(),binding.email.getText().toString(),binding.password.getText().toString());
                                        String id=task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(user);
                                        Toast.makeText(signup.this,"SignUp Successful",Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(signup.this,HomeActivity.class);
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(signup.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else if (!binding.password.getText().toString().equals(binding.Cnfpassword.getText().toString())){
                    Toast.makeText(signup.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(signup.this,"Mandatory Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup.this,SignInActivity.class);
                startActivity(i);
            }
        });

    }
}