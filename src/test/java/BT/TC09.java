package BT;

import DOM.CartPage;
import DOM.LoginPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC09 {
//      1. Go to http://live.techpanda.org/
//      2. Go to Mobile and add IPHONE to cart
//      3. Enter Coupon Code
//      4. Verify the discount generated
//      TestData:  Coupon Code: GURU50
//      Expected result:
//      1) Price is discounted by 5%
@Test
    public void test() {
        WebDriver driver = driverFactory.getChromeDriver();
            try{
            //step 1
                driver.get("http://live.techpanda.org/");

            //step 2
                WebElement mobileClick = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[1]/div[1]/div[3]/nav[1]/ol[1]/li[1]/a[1]"));
                mobileClick.click();
                CartPage cartPage = new CartPage(driver);
                cartPage.addToCartIphone();

            //step 3
                double originalPrice = cartPage.getGrandTotalPrice();
                cartPage.useCouponCode("GURU50");
                double discountPrice = cartPage.getGrandTotalPrice()*0.95;
                double afterDiscount = cartPage.getGrandTotalPrice();

            //step 4
                String notification = cartPage.getNotification();
                if(notification.isBlank() || notification != null) {
                    System.out.println(notification);
                    System.out.println("Expected: " + discountPrice);
                    System.out.println("Actual: " + afterDiscount);
                }
                Assert.assertNotEquals(afterDiscount, originalPrice);

        }catch (Exception e) {
            Assert.fail("error at "+e);
        }
    }
}
