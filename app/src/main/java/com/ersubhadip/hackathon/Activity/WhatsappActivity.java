package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ersubhadip.hackathon.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class WhatsappActivity extends AppCompatActivity {
    private AppCompatButton apiSubmitBtn;
    private TextView t1,t3;
    private LinearLayout layoutFields;
    private EditText phoneEt;
    private int option=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
        //initialisation
        apiSubmitBtn=findViewById(R.id.apiSubmitBtn);
        t1=findViewById(R.id.textView3);//--> Academic

        t3=findViewById(R.id.textView5);
        layoutFields=findViewById(R.id.linear_fields);// --> financial
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option = 1;
                layoutFields.setVisibility(View.VISIBLE);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option = 5;
                layoutFields.setVisibility(View.VISIBLE);
            }
        });
        if(option!=0){
            phoneEt.setVisibility(View.VISIBLE);
            apiSubmitBtn.setVisibility(View.VISIBLE);
            layoutFields.setVisibility(View.VISIBLE);
            option=0;
        }
        phoneEt=findViewById(R.id.mera_phoneET);
        //end
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //forcing light theme

        apiSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr_phn= phoneEt.getText().toString().trim();
                usr_phn="+91"+usr_phn;
                Log.d("####","user ne phone ye dala :- "+usr_phn);

                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");
                String s1="Hi We came to know you need academic help. We are always ready to help you. Tell us more about it.";
                String s2="Hi We came to know you need financial help. Money matters a lot in one's life. We suggest you to take our free scholarship test on Andorid Development by Vibhu Pandey- Microsoft SDE3.";
                RequestBody body=RequestBody.create(mediaType, "{\"phone\":\""+usr_phn+"\",\"text\":\""+(option==1?s1:s2)+"\"}");
                String BASE_URL = "https://rapidapi.rmlconnect.net/wbm/v1/message";
                Request request = new Request.Builder().url(BASE_URL).method("POST", body).addHeader("Content-Type", "application/json").addHeader("Authorization", "618fc5f20fcc5f0012911573").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        e.printStackTrace();
                        Log.d("####",""+e+"  --> "+e.getMessage());
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if(response.isSuccessful()){
                            Log.d("####","response was a success");
                        }
                        else{
                            Log.d("####","response was a failure  "+response.toString());
                        }
                    }
                });



            }
        });

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType= MediaType.parse("application/json");
        String sessionURL = "https://rapidapi.rmlconnect.net/wbm/v1/message";

        /****** -------------------------------------------
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutFields.setVisibility(View.VISIBLE);
                option=0;
                String number = "+91"+phoneEt.getText().toString().trim();
                RequestBody body = RequestBody.create(mediaType,"{\"phone\":\""+number+"\",\"text\":\"Hi there. We are sorry that you are facing academic troubles. Could you please elaborate ?\"}");
                Request request = new Request.Builder().url(sessionURL).method("POST",body).addHeader("Content-Type", "application/json").addHeader("Authorization", "617bf1ff245383001100f811").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        e.printStackTrace();
                        Log.d("####",""+e+"  --> "+e.getMessage());
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if(response.isSuccessful()){
                            Log.d("####","response was a success");
                        }
                        else{
                            Log.d("####","response was a failure  "+response.toString());
                        }
                    }
                });
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutFields.setVisibility(View.VISIBLE);
                option=1;
                RequestBody body = RequestBody.create(mediaType,"{\"phone\":\"+916299744625\",\"text\":\"Hi there. Would you like to apply for scholarship at EducateIndia.com\"}");
                Request request = new Request.Builder().url(sessionURL).method("POST",body).addHeader("Content-Type", "application/json").addHeader("Authorization", "617bf1ff245383001100f811").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        e.printStackTrace();
                        Log.d("####",""+e+"  --> "+e.getMessage());
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if(response.isSuccessful()){
                            Log.d("####","response was a success");
                        }
                        else{
                            Log.d("####","response was a failure  "+response.toString());
                        }
                    }
                });
            }
        });
         -----------------------------                      */

    }
}