package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {

    public CartPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    public List<WebElement> getProductList() {
        return productList;
    }

    public double getProductsSum() {
        int count = productList.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productList.get(i).getText();
            Double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;
        }
        return totalSum;
    }

    public Double getTotalAmountDisplay() {
        return getFormattedAmount(totalAmount.getText());
    }

    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void acceptTermsCondition() {
        longPressAction((org.openqa.selenium.remote.RemoteWebElement) terms);
        acceptButton.click();
    }

    public void submitOrder() {
        proceed.click();
    }
}
