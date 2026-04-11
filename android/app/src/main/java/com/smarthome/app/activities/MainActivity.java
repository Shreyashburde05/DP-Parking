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
import com.smarthome.app.patterns.factory.VehicleFactory;
import com.smarthome.app.patterns.factory.Vehicle;
import com.smarthome.app.patterns.observer.RevenueNotifier;
import com.smarthome.app.patterns.singleton.ParkingManager;
import java.util.ArrayList;
import java.util.List;

/**
 * SmartPark Pro Main Activity
 * 
 * Implements the Parking Logic:
 * 1. Singleton (ParkingManager)
 * 2. Factory (VehicleFactory)
 * 3. Observer (RevenueNotifier)
 */
public class MainActivity extends AppCompatActivity implements RevenueNotifier.RevenueObserver {

    private RecyclerView vehicleList;
    private DeviceAdapter adapter;
    private List<Vehicle> vehicles = new ArrayList<>();
    private TextView revenueText, spotsText, attendantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access the singleton (Alice)
        ParkingManager manager = ParkingManager.getInstance();
        
        attendantName = findViewById(R.id.dashboardTitle);
        attendantName.setText("Parking Pro Control");
        
        // Register for real-time revenue updates (Charlie)
        RevenueNotifier.registerObserver(this);

        setupUI();
    }

    private void setupUI() {
        revenueText = findViewById(R.id.globalStatusTitle);
        spotsText = findViewById(R.id.globalStatusSub);
        
        updateDisplay();

        vehicleList = findViewById(R.id.deviceRecyclerView);
        vehicleList.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new DeviceAdapter(vehicles);
        vehicleList.setAdapter(adapter);

        findViewById(R.id.btnAddDevice).setOnClickListener(v -> {
            // Demonstration of Factory Method in Action (Bob)
            String plate = "PB-" + (int)(Math.random()*9000+1000);
            Vehicle vh = VehicleFactory.createVehicle(VehicleFactory.TYPE_SEDAN, plate);
            
            vehicles.add(vh);
            adapter.notifyItemInserted(vehicles.size() - 1);
            
            // Interaction with Singleton and Observer (Alice & Charlie)
            ParkingManager.getInstance().recordEntry(vh.getFee());
            RevenueNotifier.notifyVehicleEntry(plate, vh.getVehicleType(), ParkingManager.getInstance().getDailyRevenue());
            
            Toast.makeText(this, "New Vehicle Registered: " + plate, Toast.LENGTH_SHORT).show();
        });
    }

    private void updateDisplay() {
        ParkingManager manager = ParkingManager.getInstance();
        revenueText.setText("Daily Total: $" + String.format("%.2f", manager.getDailyRevenue()));
        spotsText.setText("Active Entry: " + manager.getSpotsOccupied() + " Vehicles Parked");
    }

    // Observer Implementation (Charlie)
    @Override
    public void onVehicleAdded(String plate, String type, double totalRevenue) {
        updateDisplay();
        // Visual impact (Haptic feedback feel)
        revenueText.animate().alpha(0.5f).setDuration(100).withEndAction(() -> {
            revenueText.animate().alpha(1.0f).setDuration(100).start();
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RevenueNotifier.unregisterObserver(this);
    }
}
