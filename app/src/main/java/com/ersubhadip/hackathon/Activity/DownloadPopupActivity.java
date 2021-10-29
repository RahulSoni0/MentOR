package com.ersubhadip.hackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ersubhadip.hackathon.R;

import java.io.File;

public class DownloadPopupActivity extends AppCompatActivity {
    private TextView errorTv, backTv;
    private Long downloadId;
    String title, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_popup);
        backTv=findViewById(R.id.complete_download);
        errorTv=findViewById(R.id.error_download);
        registerReceiver(onCompleteDownload,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


        errorTv.setText(Html.fromHtml("<pre>Download has not started?<span style=\"color: #3598db;\"> Click here</span></pre>"));

        if (getIntent()!=null){

            title=getIntent().getStringExtra("Title");
            link=getIntent().getStringExtra("Link");

        }



        //Beginning Download jut after activity is called
        beginDownload();

        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(DownloadPopupActivity.this,TabedActivity.class);
                startActivity(it);
                finish();


            }
        });


        errorTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                beginDownload();

            }
        });
    }
    private void beginDownload(){

        DownloadManager.Request request;
        File file=new File(getExternalFilesDir(null),title);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            request=new DownloadManager.Request(Uri.parse(link))
                    .setTitle(title).setDescription("Created By MentOR")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED).setDestinationUri(Uri.fromFile(file))
                    .setRequiresCharging(false).setAllowedOverMetered(true).setAllowedOverRoaming(true);
        }else{
            request=new DownloadManager.Request(Uri.parse(link))
                    .setTitle(title).setDescription("Created By MentOR")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED).setDestinationUri(Uri.fromFile(file))
                    .setAllowedOverMetered(true).setAllowedOverRoaming(true);


        }

        DownloadManager manager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        downloadId=manager.enqueue(request);


    }

    private BroadcastReceiver onCompleteDownload=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Long currentId=intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
            if(currentId==downloadId){
                Toast.makeText(DownloadPopupActivity.this, "Download Successful", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onCompleteDownload);
    }
}