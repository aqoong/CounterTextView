package com.aqoong.lib.countertextviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aqoong.lib.countertextview.CounterTextView;

public class MainActivity extends AppCompatActivity {

    CounterTextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterview);
    }
}
