package sample;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestNG;
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
import java.time.Duration;
import java.time.LocalDateTime;

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
                .usingAnyFreePort()
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(DEVICE_NAME);
        options.setChromedriverExecutable(CHROME_DRIVER_PATH);
        options.setApp(APP_MOBILE_PATH);

        driver = new AndroidDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        formPage = new FormPage(driver);
        productCataloguePage = new ProductCataloguePage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(Consts.PROJECT_SCREENSHOT_PATH + generateFileName());
            try {
                FileUtils.copyFile(srcFile, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateFileName() {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.getDayOfMonth() + "." + dateTime.getMonthValue() + "." + dateTime.getYear() +
                "_" + dateTime.getHour() + "." + dateTime.getMinute() + "." + dateTime.getSecond() +
                ".png";
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
