package Lesson11__ReportingSystem_and_Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Base {

    static WebDriver driver;
    Actions actionsDrag;
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_actions.html");
        actionsDrag = new Actions(driver);
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

}
