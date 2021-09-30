package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.ersubhadip.hackathon.R;

public class SplashActivity extends AppCompatActivity {
    private AppCompatButton signUp;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        signUp=findViewById(R.id.SplashSignUpBtn);
        login=findViewById(R.id.SplashLogIn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(SplashActivity.this,SignUpActivity.class);
                startActivity(it);
                finish();



            }
        });

        //todo:status bar color and physical key functions

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(it);
                finish();



            }
        });

    }
}