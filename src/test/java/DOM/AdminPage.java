package DOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.sql.Driver;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By CloseBtn = By.cssSelector("a[title='close'] span");
    private final By NavBar = By.id("nav");
    private final By DBSales = By.linkText("Sales");
    private final By Orders = By.linkText("Orders");
    private final By OrderID = By.xpath("//input[@id='sales_order_grid_filter_real_order_id']");
    private final By startDate = By.xpath("/html/body/div[1]/div[4]/div/div[3]/div/div[2]/div/table/thead/tr[2]/th[3]/div/div[1]/input");
    private final By endDate = By.xpath("/html/body/div[1]/div[4]/div/div[3]/div/div[2]/div/table/thead/tr[2]/th[3]/div/div[2]/input");
    private final By SearchBtn = By.cssSelector("button[title='Search']");
    //    private final By NavBar = By.id("nav");
    //    private final By DBSales = By.linkText("Sales");
    //    private final By Orders = By.linkText("Orders");
    //    private final By DBExport = By.id("sales_order_grid_export");
    //    private final By Export = By.cssSelector("button[title='Export']");
    //User page
    public void closeMsgBox(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CloseBtn)).click();
    }
    public void goToOrders() {
        driver.findElement(NavBar).findElement(DBSales).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Orders)).click();
    }
    public void EnterToOrderID(){
        driver.findElement(OrderID).click();
        driver.findElement(OrderID).clear();
        driver.findElement(OrderID).sendKeys("100021247");
    }
    public  void EnterFindDate() {
        driver.findElement(startDate).click();
        driver.findElement(startDate).clear();
        driver.findElement(startDate).sendKeys("11/6/2023");
        driver.findElement(endDate).click();
        driver.findElement(endDate).clear();
        driver.findElement(endDate).sendKeys("11/10/2023");
    }
    public void SearchButtonClick(){
        driver.findElement(SearchBtn).click();
    }


}