package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Fan Implementation
 */
public class Fan extends SmartDevice {
    public Fan(String name) { this.name = name; this.isOn = false; }
    @Override public String getDeviceType() { return "Ceiling Fan"; }
    @Override public String getOperation() { return "Speed Control"; }
}
