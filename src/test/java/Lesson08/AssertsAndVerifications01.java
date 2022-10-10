package Lesson08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AssertsAndVerifications01 {

    WebDriver driver;
    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/bmi/");       }
    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }

    @Test
    public void Test01() {
        driver.findElement(By.id("weight")).sendKeys("45");
        driver.findElement(By.id("hight")).sendKeys("164");
        driver.findElement(By.id("calculate_data")).click();

        String expectedBmiResult = "17";
        String expectedMeansResult = "That you are too thin.";
        String actualBmiResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        String actualMeansResult = driver.findElement(By.id("bmi_means")).getAttribute("value");

        if (actualBmiResult.equals(expectedBmiResult) && actualMeansResult.equals(expectedMeansResult))
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");
        System.out.println();
    }
    @Test
    public void Test02() {
        WebElement calculateButton = driver.findElement(By.id("calculate_data"));

//        System.out.println(calculateButton.getSize().getWidth());
//        System.out.println(calculateButton.getSize().getHeight());
        System.out.println(calculateButton.getSize());
        System.out.println();
//        System.out.println(calculateButton.getLocation().getX());
//        System.out.println(calculateButton.getLocation().getY());
        System.out.println(calculateButton.getLocation());
        System.out.println();

        System.out.println();
        if (calculateButton.isEnabled() && calculateButton.isDisplayed())
            System.out.println("Calculate Button is Enabled and Displayed");
        else
            System.out.println("Calculate Button is NOT Enabled or/and NOT Displayed");
        if (calculateButton.isSelected())
            System.out.println("Calculate Button is SELECTED");
        else
            System.out.println("Calculate Button is NOT SELECTED");

        System.out.println();
        if (calculateButton.getTagName().equals("input") && calculateButton.getAttribute("value").equals("Calculate BMI"))
            System.out.println("Tag of the Button is 'input' and Text on the Button is: 'Calculate BMI'");
        else
            System.out.println("Wrong Tag or/and Wrong Text");

        if (driver.findElement(By.id("new_input")).isDisplayed()) {
            System.out.println();
            System.out.println("Fill the fields");      }

        assertTrue(driver.findElement(By.id("calculate_data")).isDisplayed());
        assertEquals(driver.findElement(By.id("calculate_data")).getAttribute("value"),"Calculate BMI");
        assertFalse(driver.findElement(By.id("new_input")).isDisplayed(),"Checking Element (new_input) is Displayed");

    }
}
