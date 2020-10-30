import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckBlackIphone extends BaseTest {

    private PersonalDataProcessingPopUp personalDataProcessingPopUp;
    private MainPage mainPage;
    private SubCategories subCategories;
    private SearchingResult searchingResult;

    double highestPrice;
    double priceWithVAT;

    @BeforeClass
    public void initiate() {
        personalDataProcessingPopUp = new PersonalDataProcessingPopUp(driver, wait);
        mainPage = new MainPage(driver, wait);
        subCategories = new SubCategories(driver, wait);
        searchingResult = new SearchingResult(driver, wait);
    }

    @Test(priority = 0)
    public void checkBeingOnAllegroPage() {
        personalDataProcessingPopUp.acceptConsent();
        mainPage.checkIfLogoDisplayed();
    }

    @Test(priority = 1, dependsOnMethods = "checkBeingOnAllegroPage")
    public void searchIphone11() {
        mainPage.searchItem("Iphone 11");
    }

    @Test(priority = 2, dependsOnMethods = "searchIphone11")
    public void selectColor() {
        subCategories.selectColor("czarny");
    }

    @Test(priority = 3, dependsOnMethods = "selectColor")
    public void countPhones() throws InterruptedException {
        System.out.println(searchingResult.countItemsOnPage());
    }

    @Test(priority = 4, dependsOnMethods = "selectColor")
    public void findTheMostExpensive() throws InterruptedException {
        searchingResult.sortResults(" cena: od najwyższej ");
        highestPrice = searchingResult.checkHighestPrice();
        System.out.println(searchingResult.parsePLNToString(highestPrice));
    }

    @Test(priority = 5, dependsOnMethods = "findTheMostExpensive")
    public void addVAT() {
        priceWithVAT = (highestPrice * 123) / 100;
        System.out.println(priceWithVAT);
    }
}
