package Lesson02_TestNG;

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

public class Locators__Basic_01 {

    WebDriver driver;
    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/");    }

    @Test
    public void Test01__IdentificationOfLogo() {
        WebElement logoById = driver.findElement(By.id("selenium_logo"));
        WebElement logoByTagName = driver.findElement(By.tagName("svg"));
        System.out.println(logoById);
        System.out.println(logoByTagName);
        System.out.println();
    }
    @Test
    public void Test02__HowManyLinks() {
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        List<String> allSeleniumLinks = new ArrayList<>();
        System.out.println("Links Available on the Page:  " + allLinks.size());
        System.out.println();

        String word = "kiwiirc";
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement link = allLinks.get(i);
            try {
                if (link.getAttribute("href").contains(word))
                    allSeleniumLinks.add("+");
//                System.out.println(link.getAttribute("href"));
            }
            catch (Exception e) {
                System.out.println("We had Exception in our Loop. Exception is:  "+e);
                System.out.println();       }

        }
        if (allSeleniumLinks.size()>0) {
            if (allSeleniumLinks.size()==1)
                System.out.println("One Link with word '"+word+"' Available on the Page.");
            else
                System.out.println("Links with word '"+word+"' Available on the Page:  "+allSeleniumLinks.size()+".");
        }
    }

    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }
}
