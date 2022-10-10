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

public class ExternalFiles03__Random {

    WebDriver driver;
    String randomNumber;
    int sum;
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.random.org/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (driver.findElement(By.xpath("//button[text()='Allow Selected']")).isDisplayed()) {
            driver.findElement(By.xpath("//button[text()='Allow Selected']")).click();        }
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();     }

    @Test
    public void Test01(){
        WebElement iFrame = driver.findElement(By.xpath("//*[@id=\"homepage-generator\"]/iframe"));
        driver.switchTo().frame(iFrame);
        driver.findElement(By.cssSelector("span[id$='max-span']")).findElement(By.tagName("input")).clear();
        driver.findElement(By.cssSelector("span[id$='max-span']")).findElement(By.tagName("input")).sendKeys("10");
        driver.findElement(By.cssSelector("span[id$='button-span']")).findElement(By.tagName("input")).click();
        randomNumber = driver.findElement(By.cssSelector("span[id$='result']")).findElement(By.tagName("span")).getText();
        System.out.println("Random Number is: "+randomNumber);
    }
    @Test
    public void Test02() throws InterruptedException {
        Thread.sleep(2000);
        driver.get("http://juliemr.github.io/protractor-demo/");
        int randomNumberInt = Integer.parseInt(randomNumber);

        WebElement listOperators = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select operator = new Select(listOperators);
        operator.selectByValue("MULTIPLICATION");
        for (int i=0; i<randomNumberInt; i++) {
            driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).sendKeys(randomNumber);
            String secondNumber = Integer.toString(i);
            driver.findElement(By.xpath("/html/body/div/div/form/input[2]")).sendKeys(secondNumber);
            driver.findElement(By.id("gobutton")).click();
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            WebElement result = driver.findElement(By.tagName("tbody")).findElement(By.className("ng-scope"));
            List<WebElement> listResults = result.findElements(By.cssSelector("td[class='ng-binding']"));
            int intermediateSum = Integer.parseInt(listResults.get(1).getText());
            sum += intermediateSum;        }

        System.out.println("The Result for Number: "+ randomNumber+ " is: " +sum);
    }
}
