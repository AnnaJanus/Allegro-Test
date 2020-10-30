import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubCategories extends BasePage {

    public SubCategories(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectColor(String color){
        String checkboxXpath = "//span[text()='"+color+"']/../..//input[@type='checkbox']";
        String labelXpath = "//span[text()='"+color+"']/../..//label";

        waitToBecomeVisible(driver.findElement(By.xpath(labelXpath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath(checkboxXpath)));
    }


}
