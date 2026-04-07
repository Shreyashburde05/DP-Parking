package com.smarthome.app.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Developed by Charlie (Member 3) – Observer Pattern
 *
 * This implementation allows multiple UI elements or background workers
 * to subscribe to real-time updates regarding device status shifts.
 * 
 * Perfect for maintaining data synchronization in multi-screen apps.
 */
public class DeviceStatusNotifier {

    // Listener Interface
    public interface StatusObserver {
        void onDeviceStatusChanged(String deviceName, String newStatus);
    }

    private static final List<StatusObserver> observers = new ArrayList<>();

    // Static registry for simulation purposes
    public static void registerObserver(StatusObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public static void unregisterObserver(StatusObserver observer) {
        observers.remove(observer);
    }

    /**
     * Broadcast changes to all subscribed modern listeners.
     * Use this when a device's on/off status flips.
     */
    public static void notifyStatusChange(String deviceName, String newStatus) {
        // Iterate through all active observers and dispatch the news
        for (StatusObserver observer : observers) {
            observer.onDeviceStatusChanged(deviceName, newStatus);
        }
    }
}
