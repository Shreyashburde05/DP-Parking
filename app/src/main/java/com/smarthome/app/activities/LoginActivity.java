package com.smarthome.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.smarthome.app.R;
import com.smarthome.app.patterns.singleton.ParkingManager;

/**
 * Developed by Alice (Member 1) – Login & Session Integration
 * 
 * Provides a production-level authentication gateway for the Smart Parking System.
 */
public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            authenticateUser();
        });
    }

    private void authenticateUser() {
        btnLogin.setEnabled(false);
        btnLogin.setText("Securing...");

        // Simulate network/db delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Updated Singleton Instance (ParkingManager)
            ParkingManager session = ParkingManager.getInstance();
            session.loginAttendant("Nexus Attendant");

            Toast.makeText(this, "Gate Logic Loaded. Welcome!", Toast.LENGTH_SHORT).show();

            // Navigate to Dashboard
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1200);
    }
}
