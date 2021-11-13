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
        t1=findViewById(R.id.textView3);

        t3=findViewById(R.id.textView5);
        layoutFields=findViewById(R.id.linear_fields);
        phoneEt=findViewById(R.id.mera_phoneET);
        //end
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //forcing light theme

        apiSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr_phn= phoneEt.getText().toString().trim();
                usr_phn="+91"+usr_phn;
                Log.d("####","user ne phone ye dala :- "+usr_phn);
                //Rapid wala aadha galat diya hai
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");
                //RequestBody body = RequestBody.create(mediaType, "{\n\t\"phone\": \"{usr_phn}\",\n    \"extra\": \"{your value}\",\n\t\"media\": {\n\t\t\"type\": \"media_template\",\n\t\t\"template_name\": \"{your template}\",\n\t\t\"lang_code\": \"{lang_code}\",\n\t\t\"body\": [\n\t\t\t{\n        \t\t\"text\": \"{variable}\"\n        \t},\n        \t{\n        \t\t\"text\": \"{variable}\"\n        \t}\n\t\t],\n        \"button\": [\n        \t{\n        \t\t\"button_no\": \"0/1\",\n                \"url\": \"{dynamic_url variable}\"\n        \t}\n        ]\n\t}\n}");
                RequestBody body = RequestBody.create(mediaType, "{\n\t\"phone\": \"{usr_phn}\",\n\t\"media\": {\n\t\t\"type\": \"media_template\",\n\t\t\"template_name\": \"{welcome}\",\n\t\t\"lang_code\": \"{en}\"");//lang_code nahi diya gaya hai I guess it to be en-us.
                String BASE_URL = "https://rapidapi.rmlconnect.net/wbm/v1/message";
                Request request = new Request.Builder().url(BASE_URL).method("POST", body).get().build();
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

    }
}