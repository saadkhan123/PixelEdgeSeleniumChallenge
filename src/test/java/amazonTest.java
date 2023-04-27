import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

public class amazonTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = WebDriverManager.chromedriver().create();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));     // It will timeout after 10 seconds

        driver.get("https://www.amazon.com/?&tag=googleglobalp-20&ref=pd_sl_7nnedyywlk_e&adgrpid=82342659060&hvpone=&hvptwo=&hvadid=585475370855&hvpos=&hvnetw=g&hvrand=993139937997072971&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9077140&hvtargid=kwd-10573980&hydadcr=2246_13468515");
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement categoryDropdown = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select = new Select(categoryDropdown);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        select.selectByVisibleText("Automotive");
        Thread.sleep(2000);
        WebElement searchIcon = driver.findElement(By.id("nav-search-submit-button"));
        searchIcon.click();

        Thread.sleep(2000);
        List<WebElement> itemList = new ArrayList<>(driver.findElements(By.xpath("//div[@class='a-section aok-relative s-image-square-aspect']")));
        itemList.get(0).click();

        WebElement addtoCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value = 'Add to Cart']")));
        addtoCartBtn.click();           // Adding the selected item into Cart

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.findElement(By.id("attach-sidesheet-checkout-button")).click();

        String pageText = driver.findElement(By.tagName("body")).getText();
        boolean containText = pageText.contains("Sign in");
        assert containText : "Guest doesn't redirected to the Sign in page";
        System.out.println("Guest successfully redirected to the Sign in page");

        WebElement emailTextField = driver.findElement(By.xpath("//input[@type='email']"));
        emailTextField.sendKeys("saeedsaad620@gmail.com");

        WebElement continueBtn = driver.findElement(By.id("continue"));
        continueBtn.click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        passwordField.sendKeys("Roxen135");

        WebElement signInBtn = driver.findElement(By.id("signInSubmit"));
        signInBtn.click();
        Thread.sleep(2000);

        String checkoutPageText = driver.findElement(By.tagName("body")).getText();
        boolean containsText = pageText.contains("Checkout");
        assert containsText : "User doesn't redirected to the check out page";
        System.out.println("User successfully redirected to the Checkout page");
        Thread.sleep(5000);

        driver.quit();
    }
}
