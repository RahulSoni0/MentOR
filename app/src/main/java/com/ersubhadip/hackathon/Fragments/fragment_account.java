package com.ersubhadip.hackathon.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ersubhadip.hackathon.Activity.AccountSettingActivity;
import com.ersubhadip.hackathon.Activity.SplashActivity;
import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.Nullable;


public class fragment_account extends Fragment {

    private LinearLayout acctSettings,share,about,logout;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private  TextView userAcctName,userAcctEmail;


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
        userAcctEmail=view.findViewById(R.id.userAcctEmail);
        userAcctName=view.findViewById(R.id.userAcctName);
        //end
        return view;
    }
    
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        //Hum chahte hain ki user ka data firebase se retrieve ho aur yahan par dikahi de
         firestore.collection("users").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
             @Override
             public void onSuccess(DocumentSnapshot documentSnapshot) {
                 if(documentSnapshot.exists()){
                     String email=documentSnapshot.getString("email");
                     String name=documentSnapshot.getString("name");
                     userAcctEmail.setText(email);
                     userAcctName.setText(name);
                 }
                 else
                     Toast.makeText(getContext(), "Data is empty for "+auth.getUid(), Toast.LENGTH_SHORT).show();
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(getContext(), "Some error occured "+e.getMessage(), Toast.LENGTH_SHORT).show();
                 Log.d("Data Retrieval","Data Snapshot se dikhate waqt fail kar gaya");
             }
         });


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
