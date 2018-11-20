package com.jungel.simple;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public void updateUI(Runnable runnable) {
        if (runnable != null) {
            mHandler.post(runnable);
        }
    }
}
