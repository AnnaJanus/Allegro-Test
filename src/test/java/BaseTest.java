import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;


    @BeforeTest
    public void loadDriver() {
        System.setProperty("webdriver.chrome.driver", "res/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://allegro.pl/");
        wait = new WebDriverWait(driver, 30);
    }
}
