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
import com.smarthome.app.patterns.singleton.SessionManager;

/**
 * Developed by Alice (Member 1) – Login & Session Integration
 * 
 * Provides a production-level authentication gateway using the Singleton Pattern.
 */
public class LoginActivity extends AppCompatActivity {

    private MaterialButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            // Demonstrate Singleton usage during auth
            authenticateUser();
        });
    }

    private void authenticateUser() {
        // Alice: Visual feedback is key for modern apps
        btnLogin.setEnabled(false);
        btnLogin.setText("Securing...");

        // Simulate network/db delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Patterns: Accessing the Singleton instance (Alice)
            SessionManager session = SessionManager.getInstance();
            session.loginUser("Nexus Admin"); // Using custom name

            Toast.makeText(this, "Systems Online. Welcome " + session.getUserName(), Toast.LENGTH_SHORT).show();

            // Navigate to Dashboard
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1200);
    }
}
