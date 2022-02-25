package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.EventListener;

public class login extends AppCompatActivity {
private EditText email , password ;
private Button login ;
private TextView signup ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailid);
        password  = findViewById(R.id.passwordid);
        login = findViewById(R.id.loginid);
        signup = findViewById(R.id.signid);
        ProgressDialog wait = new ProgressDialog(this);
        wait.setTitle("wait");
        wait.setMessage("wait");
        login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        wait.show();
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.getText().toString() ,password.getText().toString() )
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            wait.dismiss();
                            startActivity(new Intent(login.this , MainActivity.class));
                            finish();
                        }
                    }
                });
    }
});
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this , signup.class));
            }
        });




    }


}