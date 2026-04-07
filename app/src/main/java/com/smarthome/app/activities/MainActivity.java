package com.smarthome.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.smarthome.app.R;
import com.smarthome.app.adapters.DeviceAdapter;
import com.smarthome.app.patterns.factory.DeviceFactory;
import com.smarthome.app.patterns.factory.SmartDevice;
import com.smarthome.app.patterns.observer.DeviceStatusNotifier;
import com.smarthome.app.patterns.singleton.SessionManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Entry Point for the SmartHome Control Application.
 * 
 * This class coordinates the 3 patterns implemented by the team.
 * 1. Singleton (SessionManager) - Alice
 * 2. Factory (DeviceFactory) - Bob
 * 3. Observer (DeviceStatusNotifier) - Charlie
 */
public class MainActivity extends AppCompatActivity implements DeviceStatusNotifier.StatusObserver {

    private RecyclerView deviceList;
    private DeviceAdapter adapter;
    private List<SmartDevice> devices = new ArrayList<>(); // Using proper typed List
    private TextView welcomeLabel, statusBannerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Singleton Pattern Implementation (Alice's work)
        initUserSession();

        // 2. Factory Pattern Implementation (Bob's work)
        loadHomeDevices();

        // 3. Observer Pattern Implementation (Charlie's work)
        DeviceStatusNotifier.registerObserver(this);

        setupUI();
    }

    private void initUserSession() {
        SessionManager session = SessionManager.getInstance();
        if (!session.isLoggedIn()) {
            session.loginUser("Tech User");
        }
        
        welcomeLabel = findViewById(R.id.welcomeLabel);
        welcomeLabel.setText("Welcome Back, " + session.getUserName() + "!");
    }

    private void loadHomeDevices() {
        // Concrete objects created by Factory
        devices.add(DeviceFactory.createDevice(DeviceFactory.TYPE_LIGHT, "Living Room Lamp"));
        devices.add(DeviceFactory.createDevice(DeviceFactory.TYPE_FAN, "Kitchen Fan"));
        devices.add(DeviceFactory.createDevice(DeviceFactory.TYPE_LOCK, "Front Door"));
        devices.add(DeviceFactory.createDevice(DeviceFactory.TYPE_LIGHT, "Bedroom LED"));
    }

    private void setupUI() {
        statusBannerText = findViewById(R.id.globalStatusText);
        statusBannerText.setText("Monitor: Running - System Idle");

        deviceList = findViewById(R.id.deviceRecyclerView);
        deviceList.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new DeviceAdapter(devices);
        deviceList.setAdapter(adapter);

        findViewById(R.id.btnAddDevice).setOnClickListener(v -> {
            // Demonstration of Factory Method in Action
            SmartDevice newLight = DeviceFactory.createDevice(DeviceFactory.TYPE_LIGHT, "Entryway Light");
            devices.add(newLight);
            adapter.notifyItemInserted(devices.size() - 1);
            Toast.makeText(this, "New Smart Light factory-produced!", Toast.LENGTH_SHORT).show();
        });
    }

    // Observer Implementation (Charlie's Logic)
    @Override
    public void onDeviceStatusChanged(String deviceName, String newStatus) {
        statusBannerText.setText("Log: " + deviceName + " is now " + newStatus);
        // Visual feedback
        statusBannerText.animate().alpha(0.5f).setDuration(100).withEndAction(() -> {
            statusBannerText.animate().alpha(1.0f).setDuration(100).start();
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Crucial for Memory Management
        DeviceStatusNotifier.unregisterObserver(this);
    }
}
