package com.example.harshit.recorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static MediaRecorder mRecorder;
    static String mfilename;
//    static StorageReference mStorageReference;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = new ProgressDialog(this);
//        mStorageReference = FirebaseStorage.getInstance().getReference();
//        mfilename= Environment.getExternalStorageDirectory().getAbsolutePath();
//        mfilename+= "/record_audio.mp3";
        View button = findViewById(R.id.stopButton);
//        View stop = findViewById(R.id.stopButton)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,LauncherActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startRecording();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopRecording();
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mfilename);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e("recorder error", "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
//        uploadAudio();
    }

//    private void uploadAudio() {
//        StorageReference filepath =  mStorageReference.child("Audio").child("record_audio.3gp");
//
//        Uri uri = Uri.fromFile(new File(mfilename));
//
//        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//            }
//        });
//    }
}
