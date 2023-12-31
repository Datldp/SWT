package DOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.sql.Driver;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By SubmitBtn = By.cssSelector("input[type='submit']");
    //User page
    public void fillLoginPage(String email, String password) {
        WebElement emailElement = driver.findElement(By.id("email"));
        emailElement.sendKeys(email);

        WebElement passwordElement = driver.findElement(By.id("pass"));
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton(){
        WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"send2\"]"));
        System.out.println(registerButton.getText());
        registerButton.click();
    }
    //Admin page
    public void fillLoginAdminPage(String userName, String password){
        WebElement fillUserName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]"));
        fillUserName.sendKeys(userName);

        WebElement fillPassword = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/form[1]/div[1]/div[3]/input[2]"));
        fillPassword.sendKeys(password);

        WebElement clickLoginButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/form[1]/div[1]/div[5]/input[1]"));
        clickLoginButton.click();
    }

}