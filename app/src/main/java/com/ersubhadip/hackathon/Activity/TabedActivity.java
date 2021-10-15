package com.ersubhadip.hackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ersubhadip.hackathon.R;
import com.google.android.material.tabs.TabLayout;

public class TabedActivity extends AppCompatActivity {

    private TabLayout tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabed);
        tab=findViewById(R.id.tabMain);

        //setting Action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Open Source");

        //end


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
            return  true;

        }else{

            return false;

        }
    }
}