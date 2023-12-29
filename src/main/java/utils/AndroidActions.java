package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndroidActions {

    public AndroidDriver driver;
    public WebDriverWait wait;

    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void longPressAction(RemoteWebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", element.getId(),
                        "duration", 2000));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0));
        } while (canScrollMore);
    }

    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))"));
    }

    public void makePause()  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitForElementContainsText(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        return element;
    }

}
