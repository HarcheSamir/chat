package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {
private EditText email , name , password ;
private Button signup ;
private TextView login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.emailid);
        name = findViewById(R.id.nameid);
        password = findViewById(R.id.passwordid);
        login = findViewById(R.id.loginid);
        signup= findViewById(R.id.signid);
        ProgressDialog wait = new ProgressDialog(this);
        wait.setTitle("wait");
        wait.setMessage("wait");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wait.show();
                FirebaseAuth.getInstance().
                        createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    HashMap<String , String> data = new HashMap<>() ;
                                    data.put("name",name.getText().toString() );
                                    data.put("image" , "" );
                                    data.put("id" , FirebaseAuth.getInstance().getCurrentUser().getUid());
                                   FirebaseDatabase.getInstance().getReference().
                                            child("users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(data);
                                    wait.dismiss();
                                    startActivity(new Intent(signup.this , MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    finish();

                                }
                            }
                        });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this , login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
    }
}