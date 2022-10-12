package Lesson06__TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class DriversAndMethods_02 {
    WebDriver driver;
    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();    }

    @Test
    public void test01() throws InterruptedException {
        driver.get("http://google.com");
        Thread.sleep(2000);
        driver.get("http://bing.com");
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println(driver.getTitle());
//        if (driver.getPageSource().contains("bubble"))
//            System.out.println("Exists");
//        else
//            System.out.println("No Exist");
        int size = driver.getPageSource().split("Error").length-1; //-----This function is key sensitive
        if (size==0)
            System.out.println("No Exist.");
        else if (size==1)
            System.out.println("Exist 1 time.");
        else
            System.out.println("Exist "+size+" times.");
    }

    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }
}
