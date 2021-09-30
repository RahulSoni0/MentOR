package com.ersubhadip.hackathon.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ersubhadip.hackathon.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.Nullable;


public class fragment_account extends Fragment {
    private TextView logout;


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
        logout=view.findViewById(R.id.logout);
        //end
        return view;
    }
    
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);

         FirebaseAuth auth =FirebaseAuth.getInstance();
         logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 auth.signOut();
             }
         });
        
    }
}
