package Lesson10;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExternalFiles02 {

    WebDriver driver;
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://juliemr.github.io/protractor-demo/");
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void Test01__CreatingTable() {
        WebElement listOperators = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select operator = new Select(listOperators);
        operator.selectByValue("MULTIPLICATION");

        for (int j=0; j<3; j++) {
            String firstNumber = Integer.toString(j+1);
            for (int i=0; i<3; i++) {
                driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).sendKeys(firstNumber);
                String secondNumber = Integer.toString(i + 1);
                driver.findElement(By.xpath("/html/body/div/div/form/input[2]")).sendKeys(secondNumber);
                driver.findElement(By.id("gobutton")).click();
                Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            }
        }
    }
    @Test
    public void Test02__PrintingResultsReverse() {
        List<WebElement> listRows = driver.findElement(By.tagName("tbody")).findElements(By.className("ng-scope"));
        for (WebElement row : listRows) {
            List<WebElement> listResults = row.findElements(By.cssSelector("td[class='ng-binding']"));
            System.out.println(listResults.get(1).getText());        }
    }
}
