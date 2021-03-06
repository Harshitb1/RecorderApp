package com.example.harshit.recorder;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import static com.example.harshit.recorder.LauncherActivity.CHANNEL_ID;
import static com.example.harshit.recorder.MainActivity.mRecorder;
import static com.example.harshit.recorder.MainActivity.mfilename;

public class Recorder extends Service {

    StorageReference mStorageReference;
    ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("recorder1","Ibind Service");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mStorageReference = FirebaseStorage.getInstance().getReference();

        mProgressDialog = new ProgressDialog(this);
        Log.d("recorder1","Oncreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("recorder1","start command");

        mfilename= Environment.getExternalStorageDirectory().getAbsolutePath();
        mfilename+= "/record_audio.mp3";
        startRecording();

        Intent notificationIntent = new Intent(this,LauncherActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification notification= new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Safety")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();
        Log.d("recorder1","start notification");

        startForeground(1,notification);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("recorder1","On Destroy");

        stopRecording();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        Log.d("recorder1","stop Recording");

        mRecorder = null;
        uploadAudio();
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mfilename);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        Log.d("recorder1","start recording fn");

        try {
            mRecorder.prepare();
            Log.d("recorder1","mrocrder.prepare");

        } catch (IOException e) {
            Log.e("recorder_error", "prepare() failed");
        }

        mRecorder.start();
    }


    private void uploadAudio() {

        mProgressDialog.setMessage("uploading...");

//        mProgressDialog.show();

        StorageReference filepath =  mStorageReference.child("Audio").child("record_audio.mp3");

        Uri uri = Uri.fromFile(new File(mfilename));

        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                mProgressDialog.dismiss();
                Toast.makeText(Recorder.this,"Uploaded Successfully",Toast.LENGTH_LONG).show();
            }
        });


    }
}
