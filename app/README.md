# 🅿️ SmartPark - Android Design Patterns Demo

This project is a clean, modular Android application developed as a collaborative effort by a 3-member team to demonstrate key structural and behavioral design patterns in the context of a **Smart Parking Management System**.

## 👥 Team & Patterns

### 1. Developer 1: Alice (Global State Management)
**Implemented:** `Singleton Pattern`
- **File:** `patterns/singleton/ParkingManager.java`
- **Purpose:** Maintains a single, globally consistent control unit for the parking lot. Tracks total spots occupied, daily revenue, and the current attendant. Uses thread-safe double-checked locking to guarantee exactly one instance across the app lifecycle.

### 2. Developer 2: Bob (Object Creation)
**Implemented:** `Factory Pattern`
- **Files:** `patterns/factory/VehicleFactory.java`, `Vehicle.java`, `Sedan.java`, `TwoWheeler.java`, `HeavyTruck.java`
- **Purpose:** Centralizes the creation of vehicle objects (Sedan, Two-Wheeler, Heavy Truck) via a single `createVehicle(type, plate)` call. Decouples the UI from concrete vehicle classes, making it trivial to add new vehicle types.

### 3. Developer 3: Charlie (Event Broadcasting)
**Implemented:** `Observer Pattern`
- **File:** `patterns/observer/RevenueNotifier.java`
- **Purpose:** Keeps the revenue display and any registered UI listeners automatically synchronized with vehicle entry/exit events. Observers implement the `RevenueObserver` interface and are notified via `notifyVehicleEntry()` on each parking event.

### 4. Added Structural Pattern: Decorator
**Implemented:** `Decorator Pattern`
- **Files:** `patterns/decorator/VehicleDecorator.java`, `CarWashDecorator.java`, `SecurityDecorator.java`
- **Purpose:** Allows adding optional services (like Car Wash or Premium Security) to any vehicle type dynamically. This structure avoids subclass explosion by wrapping base vehicle objects with specific service decorators that modify the fee calculation and description.

---

## 🧱 Project Structure
```text
app/src/main/
├── java/com/smarthome/app/
│   ├── activities/           # SplashActivity, LoginActivity, MainActivity
│   ├── adapters/             # DeviceAdapter (RecyclerView bridging)
│   ├── patterns/             # Individual team contributions
│   │   ├── singleton/
│   │   │   └── ParkingManager.java
│   │   ├── factory/
│   │   │   ├── VehicleFactory.java
│   │   │   ├── Vehicle.java
│   │   │   ├── Sedan.java
│   │   │   ├── TwoWheeler.java
│   │   │   └── HeavyTruck.java
│   │   ├── observer/
│   │   │   └── RevenueNotifier.java
│   │   └── decorator/
│   │       ├── VehicleDecorator.java
│   │       ├── CarWashDecorator.java
│   │       └── SecurityDecorator.java
├── res/
│   ├── layout/               # Material UI XMLs (splash, login, main, item)
│   ├── values/               # themes.xml, colors.xml, strings.xml, dimens.xml
└── AndroidManifest.xml       # App configuration
```

## 🚀 How to Run
1. Open **Android Studio**.
2. Select **Import Project** and point to the `app/` directory.
3. Ensure you have the **Material Components** and **RecyclerView** libraries in your `build.gradle` (automatically handled by modern Studio builds).
4. Run on an Emulator or Physical Device.

---
*Developed with ❤️ by The Pattern Team.*
