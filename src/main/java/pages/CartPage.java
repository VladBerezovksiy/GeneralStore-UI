package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {

    public AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productsPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsConditionBtn;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeTermsConditionBtn;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement termsConditionCheckbox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement purchaseBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement pageTitleText;


    private void validateCartPageIsOpened() {
        String titleText = "Cart";
        System.out.println("Page '" + titleText.toUpperCase() + "' is opened");
        waitForElementContainsText(pageTitleText, titleText);
    }

    public double getProductsSum() {
        validateCartPageIsOpened();
        double totalSum = 0;
        for (int i = 0; i < productsPrice.size(); i++) {
            double amountPrice = getFormattedPrice(productsPrice.get(i).getText());
            totalSum += amountPrice;
        }
        return totalSum;
    }

    private double getFormattedPrice(String price) {
        return Double.parseDouble(price.replace("$", ""));
    }

    public double getTotalAmountDisplay() {
        return getFormattedPrice(totalAmount.getText());
    }

    public void acceptTermsCondition() {
        longPressAction((RemoteWebElement) termsConditionBtn);
        closeTermsConditionBtn.click();
    }

    public void submitOrder() {
        termsConditionCheckbox.click();
        purchaseBtn.click();
        makePause();
    }
}
