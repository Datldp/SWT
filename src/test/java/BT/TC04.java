package BT;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TC04 {
    @Test
    public void tc04() {
//         1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to the website
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on "MOBILE" menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3: Click on "Add To Compare" for 2 mobiles (Sony Xperia & iPhone)

        WebElement sonyXperiaCompareBtn = driver.findElement(By.cssSelector(".item:nth-child(2) .link-compare"));
        sonyXperiaCompareBtn.click();

        WebElement iPhoneCompareBtn = driver.findElement(By.cssSelector(".item:nth-child(3) .link-compare"));
        iPhoneCompareBtn.click();

        // Step 4: Click on "COMPARE" button
        WebElement compareBtn = driver.findElement(By.cssSelector("button[title='Compare']"));
        compareBtn.click();

        // Step 5: Verify the pop-up window and check the selected products
        String mainWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement compareHeading = driver.findElement(By.cssSelector("h1"));
        String expectedHeading = "COMPARE PRODUCTS";
        String actualHeading = compareHeading.getText();
        AssertJUnit.assertEquals(expectedHeading, actualHeading);

        // Step 6: Close the pop-up window
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        // Close the browser
        driver.quit();
    }
}