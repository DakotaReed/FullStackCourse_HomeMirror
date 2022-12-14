package Lesson12__DataDrivenTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

@Listeners(ListenersAuto.class)

public class DataDrivenTesting {
    static WebDriver driver;
    String textForSearching;
    String actualTitle;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }
//    @DataProvider(name = "dataProvider__Wiki")
//    public Object[][] getDataObject() {
//        return new Object[][] {
//                {"Import", "Import"},
//                {"Israel", "Israel"},
//                {"Automation", "Automation"},
//                {"BlahBlah", "Search results"}
//        };
//    }

    @Test(description = "Checking Title of the search result Page", dataProvider = "dataProvider Wiki CSV", dataProviderClass = Utils.class)
    public void checkingTitleWiki(String textForSearching, String expectedTitle) throws InterruptedException {
//        textForSearching = "BlahBlah";
        inputText(textForSearching);
        click();
        gettingTitle();
        checkingTitles(actualTitle, expectedTitle);
        Thread.sleep(1000);
        goingBack();
        clearingSearchRow();
    }

    @Step("Input Text to Search")
    public void inputText(String textForInput) {
        driver.findElement(By.id("searchInput")).sendKeys(textForInput);
    }
    @Step("Click to Search")
    public void click() {
        driver.findElement(By.className("pure-button")).click();
    }
    @Step("Getting Title of Search Results")
    public void gettingTitle() {
        actualTitle = driver.findElement(By.id("firstHeading")).getText();
    }
    @Step("Checking Titles")
    public void checkingTitles(String actualTitle, String expectedTitle) {
//        if (!actualTitle.equals("Search results"))
            assertEquals(actualTitle, expectedTitle);
    }
    @Step("Going Page Back")
    public void goingBack() {
        driver.navigate().back();
    }
    @Step("Clearing Search Row")
    public void clearingSearchRow() {
        driver.findElement(By.id("searchInput")).clear();
    }
}
