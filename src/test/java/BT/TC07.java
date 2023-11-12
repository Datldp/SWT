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

import java.time.Duration;


public class TC07 {
    /* Verify user is able to purchase product using registered email id (USE CHROME BROWSER)

Test Steps:

    1. Go to http://live.techpanda.org/

    2. Click on My Account link

    3. Login in application using previously created credential

    4. Click on 'My Orders'

    5. Click on 'View Order'

    6. Click on 'Print Order' link

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
            WebElement myOrders = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[1]/div/div[2]/ul/li[4]/a"));
            myOrders.click();

            //step 5

            WebElement viewOrder = driver.findElement(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr/td[6]/span/a[1]"));
            viewOrder.click();

            //step 6

            WebElement printOrder = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div/a[2]"));
            printOrder.click();


        }catch (Exception e) {
            Assert.fail("error at "+e);
        }
    }
}