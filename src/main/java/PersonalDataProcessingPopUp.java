import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalDataProcessingPopUp extends BasePage {

    @FindBy(css = "img[alt='zamknij']")
    WebElement closePopUpButton;

    public PersonalDataProcessingPopUp(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void acceptConsent() {
        click(closePopUpButton);
    }
}
