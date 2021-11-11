package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button apiSubmitBtn;
    private TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
        apiSubmitBtn=findViewById(R.id.apiSubmitBtn);
        t1=findViewById(R.id.textView3);
        t2=findViewById(R.id.textView4);
        t3=findViewById(R.id.textView5);
        t4=findViewById(R.id.textView6);
        apiSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr_phn= ((EditText) findViewById(R.id.mera_phoneET)).getText().toString();
                usr_phn="+91"+usr_phn;
                Log.d("####","user ne phone ye dala :- "+usr_phn);
                //Rapid wala aadha galat diya hai
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");
                //RequestBody body = RequestBody.create(mediaType, "{\n\t\"phone\": \"{usr_phn}\",\n    \"extra\": \"{your value}\",\n\t\"media\": {\n\t\t\"type\": \"media_template\",\n\t\t\"template_name\": \"{your template}\",\n\t\t\"lang_code\": \"{lang_code}\",\n\t\t\"body\": [\n\t\t\t{\n        \t\t\"text\": \"{variable}\"\n        \t},\n        \t{\n        \t\t\"text\": \"{variable}\"\n        \t}\n\t\t],\n        \"button\": [\n        \t{\n        \t\t\"button_no\": \"0/1\",\n                \"url\": \"{dynamic_url variable}\"\n        \t}\n        ]\n\t}\n}");
                RequestBody body = RequestBody.create(mediaType, "{\n\t\"phone\": \"{usr_phn}\",\n\t\"media\": {\n\t\t\"type\": \"media_template\",\n\t\t\"template_name\": \"{welcome}\",\n\t\t\"lang_code\": \"{en}\"");//lang_code nahi diya gaya hai I guess it to be en-us.
                String BASE_URL = "https://rapidapi.rmlconnect.net/wbm/v1/message";
                Request request = new Request.Builder().url(BASE_URL).method("POST", body).get().build();//header is optional toh nahi dala ----> For Welcome Template
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