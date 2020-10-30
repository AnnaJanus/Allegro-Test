import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchingResult extends BasePage {

    final String productArticleXpath = "//article[@data-item='true']";

    @FindBy(xpath = productArticleXpath)
    WebElement productArticle;

    @FindBy(css = "select[data-value]")
    WebElement sortSelector;

    @FindBy(xpath = "(//article[@data-item='true']//span[contains(@class,'_1svub')])[2]")
    WebElement priceZl;


    public SearchingResult(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public int countItemsOnPage() throws InterruptedException {
        Thread.sleep(500);
        waitForPageLoad();
        waitToBecomeVisible(driver.findElement(By.xpath(productArticleXpath)));
        int counter = driver.findElements(By.xpath(productArticleXpath)).size();
        return counter;
    }

    public void sortResults(String text) {
        waitToBecomeVisible(sortSelector);
        Select sort = new Select(sortSelector);
        sort.selectByVisibleText(text);
    }

    public double checkHighestPrice() throws InterruptedException {
        Thread.sleep(500);
        waitForPageLoad();
        List<WebElement> list = driver.findElements(By.xpath("//article[@data-item='true']//span[contains(@class,'_1svub')]"));
        String priceString;
        double priceMax = Double.MIN_VALUE;
        double priceDouble;
        for (WebElement element : list) {
            priceString = element.getText();
            priceString = priceString.replace(" zł", "");
            priceString = priceString.replace(",", ".");
            priceString = priceString.replace(" ", "");
            priceDouble = Double.parseDouble(priceString);
            if (priceMax < priceDouble) {
                priceMax = priceDouble;
            }
        }
        return priceMax;
    }

    public String parsePLNToString(double pln) {
        String price = String.valueOf(pln);
        price = price.replace(".", ",");
        price = price.concat(" zł");
        return price;
    }
}
