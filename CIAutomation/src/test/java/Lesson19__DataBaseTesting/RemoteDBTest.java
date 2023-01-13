package Lesson19__DataBaseTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class RemoteDBTest extends RemoteDB {

    WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://atidcollege.co.il/Xamples/demo");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        initSQLConnection();
    }

    @Test()
    public void Test01() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys(getCredentials().get(0));
        driver.findElement(By.name("password")).sendKeys(getCredentials().get(1));
        driver.findElement(By.id("submit")).click();
        assertEquals(driver.findElement(By.id("loggedin")).getText(), "You Are Logged in");
    }

    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
        closeDBCon();
    }

}
