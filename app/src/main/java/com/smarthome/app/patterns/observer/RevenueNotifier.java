package com.smarthome.app.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Developed by Charlie (Member 3) – Observer Pattern
 *
 * This implementation allows the display and revenue system
 * to stay synchronized with entry/exit events.
 */
public class RevenueNotifier {

    // Listener Interface
    public interface RevenueObserver {
        void onVehicleAdded(String plate, String type, double totalRevenue);
    }

    private static final List<RevenueObserver> observers = new ArrayList<>();

    public static void registerObserver(RevenueObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public static void unregisterObserver(RevenueObserver observer) {
        observers.remove(observer);
    }

    /**
     * Broadcast changes to all subscribed modern listeners.
     */
    public static void notifyVehicleEntry(String plate, String type, double revenue) {
        for (RevenueObserver observer : observers) {
            observer.onVehicleAdded(plate, type, revenue);
        }
    }
}
