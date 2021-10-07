package com.ersubhadip.hackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AccountSettingActivity extends AppCompatActivity {
    /* Humare 3 objectives hain:---
    1.) User ka profile data yahan par show ho ---->>> Done
    2.) user agar apna user_name badle toh woh server par update ho jaye ---->>> Done
    3.) Password badle toh woh bhi update ho jaye
    */
    private EditText nameEt,passwordEt,confirmPasswordEt;
    private TextView emailEt;////// -->>>  Should email be kept immutable ?? --->easy for updating auth credentials
    private FirebaseAuth auth;
    private AppCompatButton saveChanges;
    private FirebaseFirestore firestore;
    private FirebaseUser firebaseUser;
    private String email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        nameEt=findViewById(R.id.nameEt);
        emailEt=findViewById(R.id.emailEt);
        passwordEt=findViewById(R.id.passwordEt);
        confirmPasswordEt=findViewById(R.id.confirmPasswordEt);
        saveChanges=findViewById(R.id.SignUpBtn);

        //todo:update credentials

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //     ####################################  ___OBJECTIVE 1____ ################################################
        //we can use DocumentReference and CollectionReference also par yahan chota sa code hai toh directly address dekar call karenge
        firestore.collection("users").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {//agar retrieved data mein kuch hai toh
                    String name = documentSnapshot.getString("name");
                    email = documentSnapshot.getString("email");
                    emailEt.setText(email);
                    nameEt.setText(name);
                }
                else//khali hai retrieved data
                    Toast.makeText(AccountSettingActivity.this, "Your data is empty", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AccountSettingActivity.this, "Some error Occured "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ab humko userName update karna hai -->> kuch samay baad pswd bhi karnege woh thoda complicated hoga
                Map<String,Object> updateDetails =new HashMap<>();
                String name=nameEt.getText().toString().trim();
                String pswd=passwordEt.getText().toString().trim();
                String cnfPswd=confirmPasswordEt.getText().toString().trim();
                if(!name.equals("")){   //agar khaali nahi hai toh
                    //Sab copy paste
                    //ISSUE : Dialog Animation nahi aa rhai. atleast 0.5 seconds ke liye dialog show karna hai humko
                    Dialog d=new Dialog(AccountSettingActivity.this);
                    d.setContentView(R.layout.loading_dialogs);
                    d.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_bg));
                    d.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    d.setCancelable(false);
                    d.show();
                    updateDetails.put("email",emailEt.getText().toString());
                    updateDetails.put("name",name);//this would overwrite the existing name field since hashmap won't allow multiple keys or values
                    firestore.collection("users").document(auth.getUid()).set(updateDetails);
                    d.cancel();
                }
                /*
                if(!email.equals("")) {  //agar khaali nahi hai toh
                    ########## Let's Discuss before proceeding
                    //to do:
                    //Change the Email
                }
                */
                if(!(pswd.equals("") || cnfPswd.equals(""))) { // atleast ek toh bhara hai field
                    if(cnfPswd.equals(""))//cnf pswd khali hai lekin pswd bhara tha
                        confirmPasswordEt.setError("Please Confirm your password");
                    else if(pswd.equals(""))
                        passwordEt.setError("Please Enter Password");
                    else if(!pswd.equals(cnfPswd))
                        confirmPasswordEt.setError("Passwords don't match");
                    else{
                        firebaseUser=auth.getCurrentUser();//abhi kon sa banda line pe hai uske baare mein hume batao
                        AuthCredential credential = EmailAuthProvider.getCredential(email,pswd);
                        // maan ke chal rahe hain ki email nahi badla hoga
                        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(AccountSettingActivity.this, "Credentials Updated", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(AccountSettingActivity.this, "Some error Occured ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }

    //jaise hi hum back press karnege toh hum chahenge ki fragment_account updated data show kare na ki cached data
    //isko explicitly karna padta hai through onBackPressed function
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}