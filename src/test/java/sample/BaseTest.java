package sample;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import pages.CartPage;
import pages.FormPage;
import pages.ProductCataloguePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Consts;
import utils.TestListener;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.Settings.*;

@Listeners(TestListener.class)
public class BaseTest {

    protected AppiumDriverLocalService service;
    protected AndroidDriver driver;

    protected FormPage formPage;
    protected ProductCataloguePage productCataloguePage;
    protected CartPage cartPage;

    @BeforeClass
    public void configuration() {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(APPIUM_SERVER_PATH))
                .withIPAddress(APPIUM_IP_ADDRESS)
                .usingPort(APPIUM_PORT_ADDRESS)
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        String device = System.getProperty("deviceName", DEVICE_NAME);
        String appPath = System.getProperty("app", APP_MOBILE_PATH);
        options.setDeviceName(device);
        options.setApp(appPath);
        options.setAppWaitDuration(java.time.Duration.ofSeconds(60)); // wait for app loading up to 60 seconds
        options.setNewCommandTimeout(java.time.Duration.ofSeconds(120)); // increase command timeout

        URL serverUrl = service.getUrl();
        driver = new AndroidDriver(serverUrl, options);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30)); // increase implicit wait

        formPage = new FormPage(driver);
        productCataloguePage = new ProductCataloguePage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE && driver != null) {
            try {
                // Ensure directory exists
                Path dir = Paths.get(Consts.PROJECT_SCREENSHOT_PATH);
                Files.createDirectories(dir);

                // File name: <method>_yyyy-MM-dd_HH-mm-ss.png
                String fileName = testResult.getMethod().getMethodName() + "_" + timeStamp() + ".png";
                File destFile = dir.resolve(fileName).toFile();

                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile, destFile);

                // optionally - attach to report via logger/reporter
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String timeStamp() {
        LocalDateTime dt = LocalDateTime.now();
        return String.format("%04d-%02d-%02d_%02d-%02d-%02d",
                dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(),
                dt.getHour(), dt.getMinute(), dt.getSecond());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
        if (service != null && service.isRunning()) service.stop();
    }
}
