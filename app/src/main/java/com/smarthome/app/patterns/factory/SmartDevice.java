package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Base SmartDevice Class
 */
public abstract class SmartDevice {
    protected String name;
    protected boolean isOn;

    public abstract String getDeviceType();
    public abstract String getOperation();

    public String getName() { return name; }
    public boolean isOn() { return isOn; }
    public void setOn(boolean on) { isOn = on; }
}
