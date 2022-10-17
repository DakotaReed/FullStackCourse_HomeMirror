package Lesson13__PageObjectsPattern;

import Objects__PageObjectsPattern.ClickPage;
import Objects__PageObjectsPattern.FormPage;
import Objects__PageObjectsPattern.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

@Listeners(ListenersAuto.class)
public class Tests__PageObjectsPattern {

    static WebDriver driver;
    LoginPage login;
    FormPage form;
    ClickPage click;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/webdriveradvance.html");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        login = PageFactory.initElements(driver, LoginPage.class);
        form = PageFactory.initElements(driver, FormPage.class);
        click = PageFactory.initElements(driver, ClickPage.class);
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test(description = "Detection Click Button")
    public void enterTo() {
        login.logInAction("selenium", "webdriver");
        form.fiLLFormAction("Bookkeeper", "31", "Kiryat Ono");
        assertEquals(click.gettingTextButton(), "Click Me!");
    }

}
