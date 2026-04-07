package com.smarthome.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.smarthome.app.R;

/**
 * Developed by Alice (Member 1) - Visual Entry Point
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Simple Logo Animation (Developer 1 preference)
        ImageView logo = findViewById(R.id.logoImage);
        Animation fade = new AlphaAnimation(0.0f, 1.0f);
        fade.setDuration(1200);
        logo.startAnimation(fade);

        // Transition logic
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // Increased for "Premium" brand recognition (Alice's choice)
    }
}
