# General Store Mobile Application Test Automation

This project contains automated tests for the General Store Android mobile application using Appium, Selenium, and TestNG.

## Project Overview

This is a mobile test automation framework that demonstrates end-to-end testing of an Android e-commerce application. The tests cover the complete user journey from form filling to product checkout.

### Test Scenarios
- User registration with personal details
- Product browsing and selection
- Shopping cart management
- Checkout process

## Technology Stack

- **Java**: 17
- **Appium**: 9.2.0 (Java Client)
- **Selenium**: 4.16.1
- **TestNG**: 7.9.0
- **Maven**: Build automation
- **UiAutomator2**: Android automation engine
- **Page Object Model**: Design pattern for test maintenance

## Prerequisites

### Required Software
1. **Java Development Kit (JDK) 17**
   ```bash
   # Check Java version
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   # Check Maven version
   mvn -version
   ```

3. **Android SDK**
   - Download and install Android Studio
   - Set up Android SDK
   - Add platform-tools to PATH

4. **Appium Server**
   ```bash
   # Install Appium globally
   npm install -g appium
   
   # Check Appium version
   appium --version
   ```

5. **Android Emulator or Physical Device**
   - Set up Android Virtual Device (AVD) or connect physical device
   - Enable USB debugging on physical devices

### Environment Setup

#### For macOS:
1. **Add Android SDK to PATH** (add to `~/.zshrc` or `~/.bash_profile`):
   ```bash
   export PATH=$PATH:~/Library/Android/sdk/platform-tools
   export ANDROID_HOME=~/Library/Android/sdk
   ```

2. **Reload shell configuration**:
   ```bash
   source ~/.zshrc
   ```

3. **Verify ADB is accessible**:
   ```bash
   adb devices
   ```

#### For Windows:
1. Add Android SDK platform-tools to system PATH
2. Set ANDROID_HOME environment variable

## Project Structure

```
GeneralStore-UI/
├── src/
│   ├── main/java/
│   │   ├── pages/           # Page Object classes
│   │   │   ├── FormPage.java
│   │   │   ├── ProductCataloguePage.java
│   │   │   └── CartPage.java
│   │   └── utils/           # Utility classes
│   │       ├── AndroidActions.java
│   │       ├── Consts.java
│   │       ├── Settings.java
│   │       └── TestListener.java
│   └── test/
│       ├── java/sample/
│       │   ├── BaseTest.java
│       │   └── ui/
│       │       └── CheckoutProductTest.java
│       └── resources/
│           ├── testng.xml
│           └── General-Store.apk
├── pom.xml
└── README.md
```

## Configuration

### 1. Update Settings.java
Open `src/main/java/utils/Settings.java` and configure:

```java
public class Settings {
    // Appium Server Configuration
    public static final String APPIUM_SERVER_PATH = "/path/to/appium/build/lib/main.js";
    public static final String APPIUM_IP_ADDRESS = "127.0.0.1";
    public static final int APPIUM_PORT_ADDRESS = 4723;
    
    // Device Configuration
    public static final String DEVICE_NAME = "your_device_name"; // Your device/emulator name
    public static final String APP_MOBILE_PATH = "src/test/resources/General-Store.apk";
}
```

### 2. Device Setup
- **For Emulator**: Update `DEVICE_NAME` to match your AVD name (e.g., "Pixel_9_Pro_XL")
- **For Physical Device**: Update `DEVICE_NAME` to match your device ID (get from `adb devices`, e.g., "emulator-5554")

### 3. Appium Server Path
Update `APPIUM_SERVER_PATH` based on your Appium installation:
- **macOS**: `/usr/local/lib/node_modules/appium/build/lib/main.js`
- **Windows**: `C:\\Users\\{username}\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js`

## Running Tests

### 1. Start Appium Server
```bash
# Start Appium server
appium
```

### 2. Start Android Emulator/Connect Device
```bash
# List available devices
adb devices

# Start emulator (if using AVD)
emulator -avd your_avd_name
```

### 3. Run Tests

#### Run all tests:
```bash
mvn clean test
```

#### Run with specific device:
```bash
mvn clean test -DdeviceName="emulator-5554"
```

#### Run with specific app path:
```bash
mvn clean test -Dapp="path/to/your/app.apk"
```

#### Run specific test class:
```bash
mvn clean test -Dtest=CheckoutProductTest
```

### 4. View Test Results
- Test results are generated in `target/surefire-reports/`
- Screenshots on failure are saved in `src/main/resources/screenshots/`

## Test Execution Flow

1. **FormPage**: User fills personal information (name, gender, country)
2. **ProductCataloguePage**: User browses and adds products to cart
3. **CartPage**: User reviews cart, accepts terms, and completes checkout