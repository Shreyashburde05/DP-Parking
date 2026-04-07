package com.smarthome.app.patterns.singleton;

import android.util.Log;

/**
 * Developed by Alice (Member 1) – Singleton Pattern Implementation
 */
public class ParkingManager {

    private static final String TAG = "ParkingManager";
    private static volatile ParkingManager instance;

    private int totalSpotsOccupied;
    private double dailyRevenue;
    private String currentAttendant;

    private ParkingManager() {
        this.totalSpotsOccupied = 0;
        this.dailyRevenue = 0.0;
        Log.d(TAG, "ParkingManager: Single Control Unit Active.");
    }

    public static ParkingManager getInstance() {
        if (instance == null) {
            synchronized (ParkingManager.class) {
                if (instance == null) {
                    instance = new ParkingManager();
                }
            }
        }
        return instance;
    }

    public void loginAttendant(String name) {
        this.currentAttendant = name;
        Log.i(TAG, "loginAttendant: " + name + " is now managing the gate.");
    }

    public synchronized void recordEntry(double fee) {
        this.totalSpotsOccupied++;
        this.dailyRevenue += fee;
    }

    public int getSpotsOccupied() { return totalSpotsOccupied; }
    public double getDailyRevenue() { return dailyRevenue; }
    public String getAttendant() { return currentAttendant; }
}
