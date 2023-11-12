package BT;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;

public class TC05 {

    @Test
    public static void testNoMore500quantity(){
        WebDriver driver = driverFactory.getChromeDriver();
        try {

            driver.get("http://live.techpanda.org/");

            // 2. Click on "MOBILE" menu
            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();

            // 3. Click on "ADD TO CART" for Sony Xperia mobile
            WebElement sonyXperiaAddToCart = driver.findElement(By.xpath("//a[@title='Sony Xperia']/following::button[@title='Add to Cart']"));
            sonyXperiaAddToCart.click();

            // 4. Change "QTY" value to 1000 and click "UPDATE" button
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");
            TakesScreenshot beforeSC = ((TakesScreenshot) driver);
            File srcFile1 = beforeSC.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile1, new File("D:\\semester5\\swt\\test\\selenium-webdriver-java\\selenium-webdriver-java\\src\\test\\java\\image\\tc03\\Before.png"));

            WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']"));
            updateButton.click();

            // 5. Verify the error message
            WebElement errorMessage = driver.findElement(By.xpath("//li[@class='error-msg']"));
            String expectedErrorMessage = "The requested quantity for 'Sony Xperia' is not available";

            // Use assertEquals to compare the actual and expected error messages
            Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Error message doesn't match");

            // 6. Click on "EMPTY CART" link
            WebElement emptyCartLink = driver.findElement(By.linkText("Empty Cart"));
            emptyCartLink.click();

            // 7. Verify that the cart is empty
            WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[text()='SHOPPING CART IS EMPTY']"));
            if (emptyCartMessage.isDisplayed()) {
                System.out.println("Shopping cart is empty.");
            } else {
                System.out.println("Shopping cart is not empty.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}