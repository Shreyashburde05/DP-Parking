package com.smarthome.app.patterns.singleton;

import android.util.Log;

/**
 * Developed by Alice (Member 1) – Singleton Pattern Implementation
 *
 * This class handles the user session throughout the application's lifecycle.
 * It ensures that only one instance of the SessionManager exists at any given time,
 * providing a global point of access to session data such as user status and credentials.
 *
 * Pattern: Thread-safe Singleton with Lazy Initialization.
 */
public class SessionManager {

    private static final String TAG = "SessionManager";
    private static volatile SessionManager instance;

    // Session Data
    private String userName;
    private boolean isLoggedIn;
    private long loginTimestamp;

    // Private constructor prevents instantiation from other classes
    private SessionManager() {
        this.isLoggedIn = false;
        Log.d(TAG, "SessionManager: Primary Instance Initialized.");
    }

    /**
     * Standard implementation of double-checked locking for thread-safe session initialization.
     * @return The single instance of SessionManager.
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }

    public void loginUser(String name) {
        this.userName = name;
        this.isLoggedIn = true;
        this.loginTimestamp = System.currentTimeMillis();
        Log.i(TAG, "loginUser: User [" + name + "] has been successfully logged into the system.");
    }

    public void logoutUser() {
        this.userName = null;
        this.isLoggedIn = false;
        Log.i(TAG, "logoutUser: Session terminated.");
    }

    // Accessors
    public String getUserName() { return userName; }
    public boolean isLoggedIn() { return isLoggedIn; }
    public long getLoginTimestamp() { return loginTimestamp; }
}
