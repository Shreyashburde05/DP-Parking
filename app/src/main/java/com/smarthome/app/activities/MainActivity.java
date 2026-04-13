package com.smarthome.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarthome.app.R;
import com.smarthome.app.adapters.DeviceAdapter;
import com.smarthome.app.patterns.decorator.CarWashDecorator;
import com.smarthome.app.patterns.decorator.SecurityDecorator;
import com.smarthome.app.patterns.factory.VehicleFactory;
import com.smarthome.app.patterns.factory.Vehicle;
import com.smarthome.app.patterns.observer.RevenueNotifier;
import com.smarthome.app.patterns.singleton.ParkingManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RevenueNotifier.RevenueObserver {

    private final List<Vehicle> vehicles = new ArrayList<>();
    private DeviceAdapter adapter;
    private TextView revenueText, spotsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParkingManager manager = ParkingManager.getInstance();
        
        TextView attendantName = findViewById(R.id.dashboardTitle);
        String name = manager.getAttendant();
        if (name != null && !name.isEmpty()) {
            attendantName.setText(String.format("Welcome, %s", name));
        } else {
            attendantName.setText("Parking Pro Control");
        }
        
        RevenueNotifier.registerObserver(this);

        setupUI();
    }

    private void setupUI() {
        revenueText = findViewById(R.id.globalStatusTitle);
        spotsText = findViewById(R.id.globalStatusSub);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        
        updateDisplay();

        RecyclerView vehicleList = findViewById(R.id.deviceRecyclerView);
        vehicleList.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new DeviceAdapter(vehicles);
        vehicleList.setAdapter(adapter);

        findViewById(R.id.btnAddDevice).setOnClickListener(v -> {
            String plate = "PB-" + (int)(Math.random()*9000+1000);
            
            // Randomly choose a vehicle type from the Factory
            String[] types = {VehicleFactory.TYPE_SEDAN, VehicleFactory.TYPE_CYCLE, VehicleFactory.TYPE_TRUCK};
            String randomType = types[(int)(Math.random() * types.length)];
            
            // Create base vehicle using Factory Pattern (Creational)
            Vehicle vh = VehicleFactory.createVehicle(randomType, plate);
            
            // Apply Structural Pattern (Decorator)
            // 1. Randomly add Car Wash (30% chance)
            if (Math.random() < 0.3) {
                vh = new CarWashDecorator(vh);
            }
            
            // 2. Add Premium Security to all Trucks for demonstration
            if (VehicleFactory.TYPE_TRUCK.equals(randomType)) {
                vh = new SecurityDecorator(vh);
            }
            
            vehicles.add(vh);
            adapter.notifyItemInserted(vehicles.size() - 1);
            
            // Record entry in Singleton Manager (Creational)
            ParkingManager.getInstance().recordEntry(vh.getFee());
            
            // Broadcast event via Observer Pattern (Behavioral)
            RevenueNotifier.notifyVehicleEntry(plate, vh.getVehicleType(), ParkingManager.getInstance().getDailyRevenue());
            
            Toast.makeText(this, "Logged: " + vh.getVehicleType(), Toast.LENGTH_SHORT).show();
        });

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Toast.makeText(this, "Home Dashboard Active", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_stats) {
                Toast.makeText(this, "Analytics: 0 Alerts Pending", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_settings) {
                Toast.makeText(this, "Configuration Loaded", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        setupChip(R.id.chipAll, "Showing All Areas");
        setupChip(R.id.chipMainFloor, "Filtering: Main Floor");
        setupChip(R.id.chipBasement, "Filtering: Basement");

        findViewById(R.id.profileImg).setOnClickListener(v -> 
            Toast.makeText(this, "Opening Attendant Profile", Toast.LENGTH_SHORT).show()
        );
    }

    private void setupChip(int id, String message) {
        View chip = findViewById(id);
        if (chip != null) {
            chip.setOnClickListener(v -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
        }
    }

    private void updateDisplay() {
        ParkingManager manager = ParkingManager.getInstance();
        revenueText.setText(String.format(Locale.US, "Daily Total: $%.2f", manager.getDailyRevenue()));
        spotsText.setText(String.format(Locale.US, "Active Entry: %d Vehicles Parked", manager.getSpotsOccupied()));
    }

    @Override
    public void onVehicleAdded(String plate, String type, double totalRevenue) {
        updateDisplay();
        revenueText.animate().alpha(0.5f).setDuration(100).withEndAction(() -> 
            revenueText.animate().alpha(1.0f).setDuration(100).start()
        ).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RevenueNotifier.unregisterObserver(this);
    }
}
