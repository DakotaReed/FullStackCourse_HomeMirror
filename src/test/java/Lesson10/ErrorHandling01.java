package Lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;

@Listeners(ListenersAuto.class)

public class ErrorHandling01 {
    WebDriver driver;
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void Test01__TryCatch() throws InterruptedException {
        driver.findElement(By.id("btn")).click();
        Thread.sleep(5000);
        try {
            driver.findElement(By.id("checkbox")).isEnabled();        }
        catch (Exception e) { }
    }

    @Test
    public void Test02__EventListeners() throws InterruptedException {
        driver.navigate().refresh();
        driver.findElement(By.id("btn")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("checkbox"));
    }
    @Test
    public void Test03__CheckBox() throws InterruptedException {
        driver.navigate().refresh();
        driver.findElement(By.id("btn")).click();
        Thread.sleep(5000);
        assertFalse(driver.findElement(By.tagName("form")).findElement(By.tagName("div")).getAttribute("id").equals("checkbox"));
    }
}
