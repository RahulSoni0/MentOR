package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ersubhadip.hackathon.R;

public class ForgetPasswordActivity extends AppCompatActivity {
    private TextView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //initialisation
        goBack=findViewById(R.id.backTv);
        //end


        //click listeners
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });


        //todo:Firebase Auth
    }
}