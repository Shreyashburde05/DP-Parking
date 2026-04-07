package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Light Implementation
 */
public class Light extends SmartDevice {
    public Light(String name) { this.name = name; this.isOn = false; }
    @Override public String getDeviceType() { return "Smart Light"; }
    @Override public String getOperation() { return "Brightness Adjust"; }
}
