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

public class DataDrivenTesting {
    static WebDriver driver;
    Actions actionsDataDriven;
    String textForSearching;
    String actualTitle;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        actionsDataDriven = new Actions(driver);
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test(description = "Checking Title of the search result Page")
    public void checkingTitleWiki() throws InterruptedException {
        textForSearching = "BlahBlah";
        inputText(textForSearching);
        click();
        gettingTitle();
        checkingTitles(actualTitle, textForSearching);
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
    public void checkingTitles(String actualTitle, String inputtedText) {
        if (!actualTitle.equals("Search results"))
            assertEquals(actualTitle, inputtedText);
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
