package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

public class FormPage extends AndroidActions {

    public AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id = "android:id/text1")
    private WebElement countryInputField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameInputField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Male\"]")
    private WebElement maleRadioBtn;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Female\"]")
    private WebElement femaleRadioBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopBtn;


    public void setName(String name) {
        nameInputField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            maleRadioBtn.click();
        } else if (gender.equalsIgnoreCase("female")) {
            femaleRadioBtn.click();
        }
    }

    public void selectCountry(String county) {
        countryInputField.click();
        scrollToText(county);
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + county + "\"]")).click();
    }

    public void submitForm() {
        shopBtn.click();
        makePause();
    }
}
