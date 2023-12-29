package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class ProductCataloguePage extends AndroidActions {

    public AndroidDriver driver;

    public ProductCataloguePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"ADD TO CART\"]")
    private List<WebElement> addToCartBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement pageTitleText;


    private void validateProductPageIsOpened() {
        String titleText = "Products";
        System.out.println("Page '" + titleText.toUpperCase() + "' is opened");
        waitForElementContainsText(pageTitleText, titleText);
    }

    public void addItemToCartByIndex(int index) {
        validateProductPageIsOpened();
        addToCartBtn.get(index).click();
    }

    public void proceedToCart() {
        cartBtn.click();
        makePause();
    }
}
