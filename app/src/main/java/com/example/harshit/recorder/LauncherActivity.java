package com.example.harshit.recorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);
        View textView = findViewById(R.id.LauncherTextView);
        View button = findViewById(R.id.LauncherButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
