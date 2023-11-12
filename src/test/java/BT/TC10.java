package BT;

import DOM.LoginPage;
import DOM.AdminPage;
import driver.driverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;

import java.time.Duration;

public class TC10 {
     /*
     1. Go to http://live.techpanda.org/index.php/backendlogin
     2. Login the credentials provided
     3. Go to Sales-> Orders menu
     4. Input OrderId and FromDate -> ToDate
     5. Click Search button
     6. Screenshot capture.

     */
    @Test
    public void test() {
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //step 1
            driver.get("http://live.techpanda.org/index.php/backendlogin");

            //step 2
            LoginPage loginPage = new LoginPage(driver);
            loginPage.fillLoginAdminPage("user01", "guru99com");

            //step 3
            AdminPage page = new AdminPage(driver);
            page.closeMsgBox();
            page.goToOrders();

            page.EnterToOrderID();
            page.EnterFindDate();
            page.SearchButtonClick();

            //     Take screenshot
            System.out.println("ok");
            TakesScreenshot mobilePage =((TakesScreenshot)driver);
            File srcFile1= mobilePage.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile1, new File("C:\\Users\\ibaxq\\Desktop\\SWT\\SWT\\src\\test\\java\\image\\tc10\\backend.png"));

            driver.quit();
        }catch (Exception e) {
            Assert.fail("error at "+e);
        }
    }
}

