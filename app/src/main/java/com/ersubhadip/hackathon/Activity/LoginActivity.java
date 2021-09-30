package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ersubhadip.hackathon.R;

public class LoginActivity extends AppCompatActivity {
    private TextView forget;
    private AppCompatButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initialisation
        forget=findViewById(R.id.forgetPasswordTv);
        login=findViewById(R.id.LoginBtn);
        //end

        //Setting click listeners
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:firebase auth
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(it);

            }
        });



    }
}