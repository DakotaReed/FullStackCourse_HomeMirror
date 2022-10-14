package Lesson12__AutomatingGraphicElements;

import Lesson12__DataDrivenTesting.ListenersAuto;
import io.github.bonigarcia.wdm.WebDriverManager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(ListenersAuto.class)

public class GoogleMaps {

    static WebDriver driver;
    Screen screen;
    String address;
    Pattern plus;
    Pattern minus;
    Pattern searchRow;
    Pattern search;
    Pattern place1;
    Pattern place2;

    @BeforeClass
    public void startSession() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://maps.google.com/");
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        screen = new Screen();
        plus = new Pattern("C:/Automation/Files/Images/GoogleMaps/plus.png").similar(0.90);
        minus = new Pattern("C:/Automation/Files/Images/GoogleMaps/minus.png").similar(0.90);
        searchRow = new Pattern("C:/Automation/Files/Images/GoogleMaps/searchV2.png").similar(0.90);
        search = new Pattern("C:/Automation/Files/Images/GoogleMaps/search.png").similar(0.90);
        place1 = new Pattern("C:/Automation/Files/Images/GoogleMaps/place1.png").similar(0.90);
        place2 = new Pattern("C:/Automation/Files/Images/GoogleMaps/place2.png").similar(0.90);
        address = "Kiryat Ono";
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test(description = "Zoom +")
    public void Test01__zoomPlus() throws FindFailed, InterruptedException {
        zoomingIn();
        zoomingIn();
    }
    @Test(description = "Zoom -")
    public void Test02__zoomMinus() throws FindFailed, InterruptedException {
        zoomingOut();
        zoomingOut();
    }
    @Test(description = "Searching address")
    public void Test03__searchingAddress() throws FindFailed, InterruptedException {
        searching(address);
    }
    @Test(description = "Clicking Map")
    public void Test04__clickingMap() throws FindFailed, InterruptedException {
        clicking(place1);
        clicking(place2);
    }

    @Step("Zooming plus")
    public void zoomingIn() throws FindFailed, InterruptedException {
        screen.click(plus);
        Thread.sleep(1000);
    }
    @Step("Zooming minus")
    public void zoomingOut() throws FindFailed, InterruptedException {
        screen.click(minus);
        Thread.sleep(1000);
    }
    @Step("Searching address")
    public void searching(String address) throws FindFailed, InterruptedException {
        screen.type(searchRow, address);
        screen.click(search);
        Thread.sleep(3000);
    }
    @Step("Clicking")
    public void clicking(Pattern clickOnThis) throws FindFailed, InterruptedException {
        screen.click(clickOnThis);
        Thread.sleep(3000);
    }
}
