package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ersubhadip.hackathon.R;

public class RCSActivity extends AppCompatActivity {

    private final String BASE_URL = "https://rapidapi.rmlconnect.net/rcs/v1/message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_c_s);

        

    }
}