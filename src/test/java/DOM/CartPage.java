package DOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void estimateShipping() {
        try {

            selectCountry("United States");
            selectRegion("California");
            String zip = "90005";
            WebElement firstNameElement = driver.findElement(By.id("postcode"));
            firstNameElement.clear();
            firstNameElement.sendKeys(zip);

            WebElement estimate = driver.findElement(By.xpath("//*[@id=\"shipping-zip-form\"]/div/button"));
            estimate.click();

            WebElement shipFee = driver.findElement(By.xpath("//*[@id=\"co-shipping-method-form\"]/dl/dd/ul/li/label/span"));
            Assert.assertEquals(shipFee.getText().trim(),"$25.00");

            WebElement stick = driver.findElement(By.xpath("//*[@id=\"s_method_flatrate_flatrate\"]"));
            stick.click();

            WebElement updateTotal = driver.findElement(By.xpath("//*[@id=\"co-shipping-method-form\"]/div/button"));
            updateTotal.click();

            WebElement checkPrice = driver.findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span"));
            Assert.assertEquals(checkPrice.getText().trim(),"$2,525.00");

            WebElement processCheckout = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/div[3]/div/ul/li[1]/button"));
            processCheckout.click();


        }catch (Exception e) {
            Assert.fail("error at "+e);
        }
    }


    public void selectCountry(String countryName) {
        WebElement countryDropdown = driver.findElement(By.id("country"));

        Select select = new Select(countryDropdown);

        select.selectByVisibleText(countryName);
    }

    public void selectRegion(String region) {
        WebElement RegionDropdown = driver.findElement(By.id("region_id"));
        Select select = new Select(RegionDropdown);
        select.selectByVisibleText(region);

    }

    public void addToCartIphone(){
        WebElement addToCartButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[3]/button[1]/span[1]/span[1]"));
        addToCartButton.click();
    }

    public void useCouponCode(String code) {
        WebElement clickCoupon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/input[1]"));
        clickCoupon.click();
        WebElement enterCoupon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/input[1]"));
        enterCoupon.sendKeys(code);
        WebElement applyCoupon = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/button[1]/span[1]/span[1]"));
        applyCoupon.click();
    }

    public double getGrandTotalPrice(){
        return Double.parseDouble(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tfoot[1]/tr[1]/td[2]/strong[1]/span[1]")).getText().replace("$",""));
    }

    public String getNotification(){
        WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[1]/ul[1]/li[1]/span[1]"));
        return element.getText();
    }



}