package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Factory Pattern
 *
 * This factory creates different types of Smart Devices based on the provided type.
 * Simplifies object creation by centralizing instantiation logic for the device list.
 */
public class DeviceFactory {

    public static final String TYPE_LIGHT = "LIGHT";
    public static final String TYPE_FAN = "FAN";
    public static final String TYPE_LOCK = "LOCK";

    /**
     * Factory method returns a concrete implementation of SmartDevice.
     * Use this instead of manual instantiation.
     */
    public static SmartDevice createDevice(String type, String deviceName) {
        if (type == null) return null;

        switch (type.toUpperCase()) {
            case TYPE_LIGHT:
                return new Light(deviceName);
            case TYPE_FAN:
                return new Fan(deviceName);
            case TYPE_LOCK:
                return new SmartLock(deviceName);
            default:
                return null;
        }
    }
}
