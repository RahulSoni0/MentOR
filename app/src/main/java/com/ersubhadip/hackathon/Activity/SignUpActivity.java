package com.ersubhadip.hackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ersubhadip.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private TextView loginPage;
    private EditText name,email,password,confirmPassword;
    private  FirebaseAuth auth;
    private AppCompatButton signUp;
    private FirebaseFirestore firebaseFirestore;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //initialisation
        loginPage=findViewById(R.id.LogInTv);
        name=findViewById(R.id.nameEt);
        email=findViewById(R.id.emailEt);
        password=findViewById(R.id.passwordEt);
        confirmPassword=findViewById(R.id.confirmPasswordEt);
        signUp=findViewById(R.id.SignUpBtn);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        //end

        //TextWatchers

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //end TextWatchers

        //Click Listener with Auth

        signUp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                signUp.setEnabled(false);
                //Loading Dialog Creation
                Dialog d=new Dialog(SignUpActivity.this);
                d.setContentView(R.layout.loading_dialogs);
                d.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_bg)); //todo:remove suppressLint function not applied for bg
                d.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                d.setCancelable(false);
                //end
                d.show();


                //final checks
                if(email.getText().toString().trim().matches(emailPattern)){
                    if(password.getText().toString().trim().equals(confirmPassword.getText().toString().trim()) && password.getText().toString().trim().length()>=6){

                        String emailData=email.getText().toString();
                        String passwordData=password.getText().toString();
                        auth.createUserWithEmailAndPassword(emailData,passwordData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    auth.signInWithEmailAndPassword(emailData,passwordData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){

                                                //sending userData to fireStore
                                                String userName=name.getText().toString().trim();
                                                Map<String,Object> userData=new HashMap<>();
                                                userData.put("name",userName);
                                                userData.put("email",emailData);
                                                firebaseFirestore.collection("users").document(auth.getUid()).set(userData);
                                                //end

                                                d.dismiss();     //Removing loading dialog after online process Completes

                                                Toast.makeText(SignUpActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                                                Intent in = new Intent(SignUpActivity.this,MainActivity.class);
                                                startActivity(in);
                                                finish();

                                            }else{

                                                d.dismiss();
                                                Toast.makeText(SignUpActivity.this, "Some Error Occurred"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                signUp.setEnabled(true);
                                            }
                                        }
                                    });

                                }
                                else{
                                    d.dismiss();
                                    Toast.makeText(SignUpActivity.this, "Some Error Occurred"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    signUp.setEnabled(true);
                                }
                            }
                        });



                    }else{
                        d.dismiss();
                        confirmPassword.setError("Please Enter Same Password");
                        signUp.setEnabled(true);

                    }


                }else{
                    d.dismiss();
                    email.setError("Please Enter Valid Email");
                    signUp.setEnabled(true);

                }

                signUp.setEnabled(true);
            }
        });











        //end

        //setting click listeners
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent it=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(it);


            }
        });


    }

    //Validating entered Information
    private  void checkInputs(){

        if(!name.getText().toString().trim().equals("")){

            if(!email.getText().toString().trim().equals("")){

                if(!password.getText().toString().trim().equals("")){

                    if (!confirmPassword.getText().toString().trim().equals("")){

                        if(password.getText().toString().trim().length()>=6){

                            signUp.setEnabled(true);

                        }else{
                            password.setError("Please Enter Valid Password!");
                            signUp.setEnabled(false);
                        }
                        }else{
                        confirmPassword.setError("Please Re-enter Password!");
                        signUp.setEnabled(false);
                    }
                }else{
                    password.setError("Please Enter Password!");
                    signUp.setEnabled(false);
                }
            }else{
                email.setError("Please Enter Email!");
                signUp.setEnabled(false);
            }
        }else{
            name.setError("Please Enter Name!");
            signUp.setEnabled(false);
        }
    }
    //end
}