import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class BasePage {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        waitToBecomeClickable(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitToBecomeClickable(element);
        element.sendKeys(text);
    }

    public void waitToBecomeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            Assert.fail("WebElement didn't become visible", e);
        } catch (NoSuchElementException e1) {
            Assert.fail("Element couldn't be located", e1);
        } catch (StaleElementReferenceException e2) {
            Assert.fail("WebElement no longer exists as initially defined", e2);
        }
    }

    public void waitToBecomeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            Assert.fail("WebElement didn't become visible", e);
        } catch (NoSuchElementException e1) {
            Assert.fail("Element couldn't be located", e1);
        } catch (StaleElementReferenceException e2) {
            Assert.fail("WebElement no longer exists as initially defined", e2);
        }
    }

    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").equals("complete");
            }
        });
    }
}
