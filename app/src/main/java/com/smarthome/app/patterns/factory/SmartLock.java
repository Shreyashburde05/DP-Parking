package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Lock Implementation
 */
public class SmartLock extends SmartDevice {
    public SmartLock(String name) { this.name = name; this.isOn = true; }
    @Override public String getDeviceType() { return "Safety Lock"; }
    @Override public String getOperation() { return "Secure/Release"; }
}
