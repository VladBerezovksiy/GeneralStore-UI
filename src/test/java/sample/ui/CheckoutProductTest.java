package sample.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import sample.BaseTest;

public class CheckoutProductTest extends BaseTest {

    @Test
    private void test01_checkoutProducts() {
        formPage.setName("Vlad");
        formPage.setGender("Male");
        formPage.selectCountry("Austria");
        formPage.submitForm();

        productCataloguePage.addItemToCartByIndex(0);
        productCataloguePage.addItemToCartByIndex(0);
        productCataloguePage.proceedToCart();

        Assert.assertEquals(cartPage.getProductsSum(), cartPage.getTotalAmountDisplay(),
                "Don't matched values");
        cartPage.acceptTermsCondition();
        cartPage.submitOrder();
    }
}
