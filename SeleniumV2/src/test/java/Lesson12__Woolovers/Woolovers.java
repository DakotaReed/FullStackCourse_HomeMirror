package Lesson12__Woolovers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.*;

@Listeners(ListenersAuto.class)

public class Woolovers {
    static WebDriver driver;
    WebDriverWait wait;
    Actions actionsWool;
    int lastPageNumber;
    String section;
    String country;
    String currency;
    String sortType;
    String price;

    @BeforeClass
    public void startSession() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wooloverslondon.com/");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        actionsWool = new Actions(driver);
        wait = new WebDriverWait(driver, 6);
        Thread.sleep(2000);
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test(description = "Close Pop Ups")
    public void Test01__ClosePopUps() {
        closingPopUps();
    }
    @Test(description = "Choose Section")
    public void Test02__ChooseNewIn () {
        section = "New In";
        choosingSection(section);
    }
    @Test(description = "Change Currency")
    public void Test03__changeCurrency() throws InterruptedException {
        clickingCountryRow();
        Thread.sleep(1000);
        country = "VI";
        selectingCountry(country);
        Thread.sleep(1000);
        currency = "US Dollar";
        selectingCurrency(currency);
        Thread.sleep(1000);
        clickingSave();
        Thread.sleep(1000);
    }
    @Test(description = "Filter by Gender: Male")
    public void Test04__filterByGender() throws InterruptedException {
        clickingGenderMenu();
        Thread.sleep(1000);
        choosingGender();
        Thread.sleep(1000);
        cancelingGender();
        Thread.sleep(1000);
    }
    @Test(description = "Sort by")
    public void Test05__sort() throws InterruptedException {
        sortType = "Price Low to High";
        Thread.sleep(1000);
        choosingSortBy();
        choosingSortType(sortType);
        Thread.sleep(1000);
        clickingDone();
        Thread.sleep(1000);
    }
    @Test(description = "Checking that Price is NOT high than 206$")
    public void Test06__checkingPrice() {
        checkingPriceOnOnePage();
        pagesCount();
        printAllPages(lastPageNumber);
        for (int i=0; i<lastPageNumber-1; i++) {
            nextPage();
            checkingPriceOnOnePage();
        }
    }

    @Step("Closing Pop Up Messages")
    public void closingPopUps() {
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.className("glClose")).click();
    }
    @Step("Choosing Section")
    public void choosingSection(String section) {
        driver.findElement(By.className("font-weight-bold")).click();
    }
    @Step("clicking Country Row")
    public void clickingCountryRow() {
        driver.findElement(By.className("country-selection--rest-of-world")).click();
    }
    @Step("Selecting Country")
    public void selectingCountry(String country) {
        ((JavascriptExecutor) driver).executeScript("scrollTo(0,1000);");
        Select countries = new Select(driver.findElement(By.id("gle_selectedCountry")));
        countries.selectByValue(country);
    }
    @Step("Selecting Currency")
    public void selectingCurrency(String currency) {
        Select countries = new Select(driver.findElement(By.id("gle_selectedCurrency")));
        countries.selectByVisibleText(currency);
    }
    @Step("Clicking Save")
    public void clickingSave() {
        driver.findElement(By.cssSelector("input[class='glDefaultBtn glCancelBtn']")).click();
        driver.switchTo().defaultContent();
    }
    @Step("Clicking Gender Menu")
    public void clickingGenderMenu() {
        driver.findElement(By.cssSelector("div[class='filter-group dropdown is-selected']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='nav-link js-filter-link']/span")));
    }
    @Step("Choosing Gender")
    public void choosingGender() {
        driver.findElement(By.xpath("//a[@class='nav-link js-filter-link']/span")).click();
    }
    @Step("Canceling Gender")
    public void cancelingGender() {
        driver.findElement(By.xpath("//a[@class='nav-link js-filter-link is-selected']/span")).click();
    }
    @Step("Choosing Sort By")
    public void choosingSortBy() {
        driver.findElement(By.id("dd-Sort By")).click();
    }
    @Step("Choosing Sort Type")
    public void choosingSortType(String sortType) {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[3]/div[2]/div[10]/div/div/div[1]/a[2]")).click();
    }

    @Step("Clicking Done")
    public void clickingDone() {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[3]/div[2]/div[10]/div/div/div[3]/div")).click();
    }
    @Step("Checking Price on one Page")
    public void checkingPriceOnOnePage() {
        char[] noHundreds = {'9','8','7','6','5','4','3'};
        char ourMaxHundred = '2';
        char[] noTens = {'9','8','7','6','5','4','3','2','1'};
        char ourMaxTens = '0';
        char[] noDollars = {'9','8','7'};
        List <String> listNumbersPrices = new ArrayList<>();
        List<WebElement> listPrices = driver.findElements(By.xpath("//p[@style='visibility: visible;']/span"));
        for (WebElement product : listPrices) {
//            String priceStr = product.getAttribute("innerHTML / innerText").replaceFirst("^.|.$","");
            String priceStr = product.getAttribute("textContent").replaceAll("^.|.$", "");
            price = priceStr.substring(0, priceStr.length()-2);
            listNumbersPrices.add(price);        }

        for (String a : listNumbersPrices) {
            char[] bbb = a.toCharArray();
            bbb = ArrayUtils.removeElement(bbb, bbb[0]);
            assertTrue(bbb.length<4);
            if (bbb.length==3) {
                for (char noHun : noHundreds)
                    assertFalse(bbb[0]==noHun);
                if (bbb[0]==ourMaxHundred) {
                    for (char noTen : noTens)
                        assertFalse(bbb[1]==noTen);
                    if (bbb[1]==ourMaxTens) {
                        for (char noDollar : noDollars)
                            assertFalse(bbb[2]==noDollar);
                    }
                }
            }
        }

    }
    @Step("All Pages Count")
    public void pagesCount() {
        List<WebElement> listPages = driver.findElements(By.xpath("//ul[@class='listing-navigation__view']/li"));
        lastPageNumber = Integer.parseInt(listPages.get(listPages.size()-2).getText());
    }
    @Step("All Pages that We Have")
    public void printAllPages(int allPages) {
        System.out.println(allPages);
    }
    @Step("Next Page")
    public void nextPage() {
            driver.findElement(By.linkText("Next")).click();
    }
}
