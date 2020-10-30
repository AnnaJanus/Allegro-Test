import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    @FindBy(css = "img[itemprop='logo']")
    WebElement logoImg;

    @FindBy(css = "input[type='search']")
    WebElement searchBox;

    @FindBy(css = "button[data-role='search-button']")
    WebElement searchButton;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkIfLogoDisplayed() {
        waitToBecomeVisible(logoImg);
    }

    public void searchItem(String text) {
        sendKeys(searchBox, text);
        click(searchButton);
    }
}
