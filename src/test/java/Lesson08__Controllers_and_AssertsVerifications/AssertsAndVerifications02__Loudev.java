package Lesson08__Controllers_and_AssertsVerifications;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Lesson08__Controllers_and_AssertsVerifications.Loudev__Support.numberOfElem;

public class AssertsAndVerifications02__Loudev {

    WebDriver driver;
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loudev.com/");
        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();
    }
    @AfterClass
    public void closeSession() {
        driver.quit();
    }

    @Test
    public void Test01() {
        Loudev__Support.verifyElements(driver);
        System.out.println("Elems in Left Table are from 3 to "+numberOfElem);
    }
}
