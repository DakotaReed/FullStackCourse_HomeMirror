package Lesson06_TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DriversAndMethods_01 {

    @Test
    public void test01() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://imdb.com");
        driver.navigate().refresh();
        String actualTitle = driver.getTitle();
        String expectedTitle = "IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";
        if (actualTitle.equals(expectedTitle))
            System.out.println("TestTitle Passed");
        else
            System.out.println("TestTitle Failed");
        String expectedUrl = "https://www.imdb.com/";
        if (driver.getCurrentUrl().equals(expectedUrl))
            System.out.println("TestUrl Passed");
        else
            System.out.println("TestUrl Failed");

        driver.quit();
    }
}
