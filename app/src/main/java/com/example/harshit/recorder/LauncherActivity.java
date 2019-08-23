package com.example.harshit.recorder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class LauncherActivity extends AppCompatActivity {

    public static final String CHANNEL_ID ="abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);
        View textView = findViewById(R.id.LauncherTextView);
        View button = findViewById(R.id.LauncherButton);
        View stop = findViewById(R.id.Stop);
        final Intent intent = new Intent(LauncherActivity.this,Recorder.class);
        final Intent intent1 = new Intent(LauncherActivity.this,messageService.class);
        final Intent intent2 = new Intent(LauncherActivity.this,MyService.class);

//        AmazonS3 s3Client = new AmazonS3Client(new BasicSessionCredentials("AKIATJEQJKLYZH2JIAMM", "DF/Ct9pd+fFg7tvixs3hzA15qAf9mJPN9qDgIF1m"));
        Log.d("recorder1","oncreate Launcher");
        createNotificationChannel();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("recorder1","start service");
                startService(intent);
                startService(intent1);
                startService(intent2);


            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("recorder1","stop service");
                stopService(intent);
                stopService(intent1);
                stopService(intent2


                );
            }
        });
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel servicechannel= new NotificationChannel(CHANNEL_ID,"abc", NotificationManager.IMPORTANCE_DEFAULT);
            Log.d("recorder1","noti channel");

            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(servicechannel);
        }
    }
}
