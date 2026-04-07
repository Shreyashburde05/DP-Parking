package com.smarthome.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.smarthome.app.R;
import com.smarthome.app.patterns.factory.SmartDevice;
import com.smarthome.app.patterns.observer.DeviceStatusNotifier;
import java.util.List;

/**
 * Developed by Charlie (Member 3) – RecyclerView Adapter
 *
 * This adapter manages the visualization of dynamic devices created by the factory.
 * It now uses typed SmartDevice objects for robust performance.
 */
public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    // Dynamic list that holds objects created by the Factory (Pattern 2)
    private final List<SmartDevice> devices;

    public DeviceAdapter(List<SmartDevice> devices) {
        this.devices = devices;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        // Now using proper typed access instead of reflection
        SmartDevice device = devices.get(position);
        
        holder.deviceName.setText(device.getName());
        holder.deviceType.setText(device.getDeviceType() + " - " + device.getOperation());
        
        // Interaction logic (Triggering Charlie's Observer Pattern)
        holder.statusSwitch.setOnCheckedChangeListener(null); // Clear previous listener
        holder.statusSwitch.setChecked(device.isOn());
        
        holder.statusSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            device.setOn(isChecked);
            String statusMsg = isChecked ? "ACTIVE" : "IDLE";
            // Global status update via Observer (Pattern 3)
            DeviceStatusNotifier.notifyStatusChange(device.getName(), statusMsg);
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    // Modern ViewHolder following Team Best Practices
    static class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView deviceName, deviceType;
        ImageView deviceIcon;
        SwitchMaterial statusSwitch;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.deviceName);
            deviceType = itemView.findViewById(R.id.deviceType);
            deviceIcon = itemView.findViewById(R.id.deviceIcon);
            statusSwitch = itemView.findViewById(R.id.statusSwitch);
        }
    }
}
