package Lesson02_TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Locators__Advanced {

    WebDriver driver;
    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_locators.html");       }
    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }

    @Test
    public void Test01__FindElementsOnPageOfYoni() {
        System.out.println(driver.findElement(By.id("locator_id")));
        System.out.println(driver.findElement(By.name("locator_name")));
        System.out.println(driver.findElement(By.id("contact_info_left")).findElement(By.tagName("p")));
        System.out.println(driver.findElement(By.className("locator_class")));
        System.out.println(driver.findElement(By.linkText("myLocator(5)")));
        System.out.println(driver.findElement(By.partialLinkText("(6)")));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"contact_info_left\"]/input")));
        System.out.println(driver.findElement(By.cssSelector("button[class='btn btn-2']")));
        System.out.println();
    }
    @Test
    public void Test02__PrintingText() {
        System.out.println(driver.findElement(By.id("locator_id")).getText());
        System.out.println(driver.findElement(By.name("locator_name")).getText());
        System.out.println(driver.findElement(By.id("contact_info_left")).findElement(By.tagName("p")).getText());
        System.out.println(driver.findElement(By.className("locator_class")).getText());
        System.out.println(driver.findElement(By.linkText("myLocator(5)")).getText());
        System.out.println(driver.findElement(By.partialLinkText("(6)")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id=\"contact_info_left\"]/input")).getAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector("button[class='btn btn-2']")).getText());
    }
}
