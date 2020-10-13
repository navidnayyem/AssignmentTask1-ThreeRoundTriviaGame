package com.example.assignment_task1_threeroundtriviagame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);

        int score_earned = getIntent().getIntExtra("scores", 0);
        textView.setText("Score Earned : " + score_earned);
    }
}