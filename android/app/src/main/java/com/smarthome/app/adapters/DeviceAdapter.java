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
import com.smarthome.app.patterns.factory.Vehicle;
import java.util.List;

/**
 * Developed by Charlie (Member 3) – RecyclerView Adapter
 *
 * This adapter manages the visualization of dynamic parking entries.
 */
public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    private final List<Vehicle> vehicles;

    public DeviceAdapter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        
        holder.deviceName.setText(vehicle.getPlate());
        holder.deviceType.setText(vehicle.getVehicleType() + " - Entry Fee: $" + vehicle.getFee());

        // Status switch indicates "Parked" state for the demo
        holder.statusSwitch.setClickable(false);
        holder.statusSwitch.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

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
