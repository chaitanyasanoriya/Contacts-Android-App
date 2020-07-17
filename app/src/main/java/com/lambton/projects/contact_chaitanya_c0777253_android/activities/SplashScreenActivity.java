package com.lambton.projects.contact_chaitanya_c0777253_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.lambton.projects.contact_chaitanya_c0777253_android.R;

public class SplashScreenActivity extends AppCompatActivity
{
    private static final long DELAYED_DURATION = 2000;
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mLinearLayout = findViewById(R.id.linear_layout);
        mLinearLayout.setAlpha(0);
        mLinearLayout.animate().alpha(1).setDuration(1250).start();
        new Handler().postDelayed(() ->
        {
            Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        },DELAYED_DURATION);
    }
}