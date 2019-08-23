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

        Log.d("recorder1","oncreate Launcher");
        createNotificationChannel();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("recorder1","start service");
                startService(intent);

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("recorder1","stop service");
                stopService(intent);
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
