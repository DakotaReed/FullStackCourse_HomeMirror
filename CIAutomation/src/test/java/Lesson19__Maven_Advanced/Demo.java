package Lesson19__Maven_Advanced;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

//    java -jar jenkins.war --httpPort=9090
public class Demo {

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
        else {
            System.out.println("TestTitle Failed");
            fail(); }
        String expectedUrl = "https://www.imdb.com/";
        if (driver.getCurrentUrl().equals(expectedUrl))
            System.out.println("TestUrl Passed");
        else {
            System.out.println("TestUrl Failed");
            fail(); }

        driver.quit();
    }

    //  mvn validate
    //  mvn compile
    //  mvn test -PExecuteTests (pom id)

}
