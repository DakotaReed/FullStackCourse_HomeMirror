package Lesson07__Locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class Locators__Basic_02 {
    WebDriver driver;
    WebElement logoPicture;
    WebElement fullRowOfInput;
    WebElement searchLanguage;
    WebElement pleaseDonate;
    List<WebElement> listOfElements = new ArrayList<>();

    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");       }

    @Test
    public void Test01__IdentificationOfElements() {
        logoPicture = driver.findElement(By.className("central-featured-logo"));
//        System.out.println(logoPicture.getAttribute("src"));
        fullRowOfInput = driver.findElement(By.tagName("fieldset"));
        searchLanguage = driver.findElement(By.id("searchLanguage"));
        pleaseDonate = driver.findElement(By.className("footer-sidebar-content"));
    }
    @Test
    public void Test02__BuildingTheList() {
        listOfElements.add(logoPicture);
        listOfElements.add(fullRowOfInput);
        listOfElements.add(searchLanguage);
        listOfElements.add(pleaseDonate);
    }
    @Test
    public void Test03__PrintingReverse() {
        for(int i=listOfElements.size()-1;i>=0;i--)
            System.out.println(listOfElements.get(i));
    }

    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }
}
