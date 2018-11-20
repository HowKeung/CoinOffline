package com.jungel.simple;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TestEosActivity extends BaseActivity {

    private static final String TAG = "TestEosActivity";

    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eos);
        textInfo = findViewById(R.id.textInfo);
    }


}
