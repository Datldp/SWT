package BT;

import DOM.CartPage;
import DOM.CheckOutPage;
import DOM.LoginPage;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import driver.driverFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TC08 {
    /* Verify user is able to purchase product using registered email id (USE CHROME BROWSER)

Test Steps:

    1. Go to http://live.techpanda.org/

    2. Click on my account link

    3. Login in application using previously created credential

    4. Click on 'REORDER' link , change QTY & click Update

    5. Verify Grand Total is changed

    6. Complete Billing & Shipping Information

    7. Verify order is generated and note the order number

    Expected outcomes:

    1) Grand Total is Changed

    2) Order number is generated

*/

    @Test
    public void test() {
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //step 1
            driver.get("http://live.techpanda.org/");

            //step 2
            WebElement accountLink = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a/span[2]"));
            accountLink.click();

            // step 3
            WebElement Login = driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a"));
            Login.click();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.fillLoginPage("ibaxq26@gmail.com","123456");
            loginPage.clickLoginButton();

            //step 4
            WebElement reorder = driver.findElement(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr/td[6]/span/a[2]"));
            reorder.click();

            WebElement changeQty = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input"));
            changeQty.clear();
            changeQty.sendKeys("5");

            WebElement clickUpdate = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/button"));
            clickUpdate.click();

            //step 5

            CartPage cartPage = new CartPage(driver);
            cartPage.estimateShipping();

            //step 6

            WebElement conti1 = driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button"));
            conti1.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")));

            element.click();

            WebElement payment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"p_method_checkmo\"]")));
            payment.click();

            WebElement paymentCountinue = driver.findElement(By.xpath("//*[@id=\"payment-buttons-container\"]/button"));
            paymentCountinue.click();

            WebElement oder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"review-buttons-container\"]/button/span/span")));
            oder.click();

            wait.until(ExpectedConditions.urlContains("http://live.techpanda.org/index.php/checkout/onepage/success/"));

            //step 7
            
            WebElement check = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div[1]/h1")));
            Assert.assertEquals(check.getText().trim(),"YOUR ORDER HAS BEEN RECEIVED.");



        }catch (Exception e) {
            Assert.fail("error at "+e);
        }
    }
}