package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ersubhadip.hackathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    private TextView loginPage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //initialisation
        loginPage=findViewById(R.id.LogInTv);
        //end

        //todo:Firebase Auth

        //setting click listeners
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent it=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(it);


            }
        });


    }
}