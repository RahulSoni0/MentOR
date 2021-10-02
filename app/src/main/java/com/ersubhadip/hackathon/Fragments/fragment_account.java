package com.ersubhadip.hackathon.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ersubhadip.hackathon.Activity.AccountSettingActivity;
import com.ersubhadip.hackathon.Activity.SplashActivity;
import com.ersubhadip.hackathon.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.Nullable;


public class fragment_account extends Fragment {

    private LinearLayout acctSettings,share,about,logout;



    public fragment_account() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        //initialisation
        acctSettings=view.findViewById(R.id.AccountLinear);
        share=view.findViewById(R.id.shareLinear);
        about=view.findViewById(R.id.aboutLinear);
        logout=view.findViewById(R.id.logoutLinear);
        //end
        return view;
    }
    
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);

         //Click Listeners
         acctSettings.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent settings=new Intent(getContext(), AccountSettingActivity.class);
                 getActivity().startActivity(settings);



             }
         });
         share.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 //todo:share intent


             }
         });

         about.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 //todo:about activity


             }
         });

         logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 FirebaseAuth.getInstance().signOut();
                 Intent it=new Intent(getContext(), SplashActivity.class);
                 startActivity(it);
                 getActivity().finish();
             }
         });
         //End



     }
}
