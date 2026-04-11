# 🏠 SmartHome Control - Android Design Patterns Demo

This project is a clean, modular Android application developed as a collaborative effort by a 3-member team to demonstrate key structural and behavioral design patterns.

## 👥 Team & Patterns

### 1. Developer 1: Alice (Structural & Session)
**Implemented:** `Singleton Pattern`
- **File:** `patterns/singleton/SessionManager.java`
- **Purpose:** Manages the global user session state (login/logout) across the app lifecycle. Alice used a thread-safe, double-checked locking mechanism for high reliability.

### 2. Developer 2: Bob (Object Creation)
**Implemented:** `Factory Pattern`
- **File:** `patterns/factory/DeviceFactory.java`
- **Purpose:** Centralizes the creation of various Smart Devices (Lights, Fans, Locks). Bob's implementation decouples the UI from concrete classes, allowing easy extension of device types.

### 3. Developer 3: Charlie (State & Communication)
**Implemented:** `Observer Pattern`
- **File:** `patterns/observer/DeviceStatusNotifier.java`
- **Purpose:** Enables real-time UI synchronization. When a device status is toggled in the list, the Dashboard's global status banner updates automatically via the observer registry.

---

## 🧱 Project Structure
```text
app/src/main/
├── java/com/smarthome/app/
│   ├── activities/      # Splash & Dashboard
│   ├── adapters/        # RecyclerView bridging
│   ├── patterns/        # Individual team contributions
│   │   ├── singleton/
│   │   ├── factory/
│   │   └── observer/
├── res/
│   ├── layout/          # Modern Material UI XMLs
│   ├── values/          # Themes, Colors, Strings
└── AndroidManifest.xml  # App configuration
```

## 🚀 How to Run
1. Open **Android Studio**.
2. Select **Import Project** and point to the `app/` directory.
3. Ensure you have the **Material Components** and **RecyclerView** libraries in your `build.gradle` (automatically handled by modern Studio builds).
4. Run on an Emulator or Physical Device.

---
*Developed with ❤️ by The Pattern Team.*
