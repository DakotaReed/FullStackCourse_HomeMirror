package Lesson09;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class Synchronization {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");        }
    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }

    @Test
    public void Test01__Rendered() {
        driver.findElement(By.id("rendered")).click();
        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish2")));
        assertTrue(driver.findElement(By.id("finish2")).isEnabled());
    }
    @Test
    public void Test02__Hidden() throws InterruptedException {
        driver.findElement(By.id("hidden")).click();
//        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.id("loading1")).isEnabled());
    }
    @Test
    public void Test03__Remove() {
        driver.findElement(By.id("checkbox")).findElement(By.tagName("input")).click();
        driver.findElement(By.id("btn")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.id("message")).isEnabled());
    }
}
