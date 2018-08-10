package com.example.harshit.recorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaRecorder mRecorder;
    String mfilename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfilename= Environment.getExternalStorageDirectory().getAbsolutePath();
        mfilename+= "/record_audio.3gp";
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
    }
}
