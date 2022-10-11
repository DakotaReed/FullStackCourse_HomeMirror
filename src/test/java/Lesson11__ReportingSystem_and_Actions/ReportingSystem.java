package Lesson11__ReportingSystem_and_Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;

import static org.testng.AssertJUnit.fail;

@Listeners(ListenersAuto.class)

public class ReportingSystem {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getData("Url"));
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    @Attachment(value = "Page Screen-Shot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step ("Filling Form")
    public void fillForm(String weight, String height) {
        driver.findElement(By.id("weight")).sendKeys(weight);
        driver.findElement(By.id("hight")).sendKeys(height);
        driver.findElement(By.id("calculate_data")).click();
    }

    @Test (description = "BMI Calculeting")
    @Description ("BMI Calculeting + getData")
    public void Test01() {
        fillForm(getData("Weight"), getData("Height"));
        String expectedBmiResult = getData("ExpectedResultBMI");
        String expectedMeansResult = getData("ExpectedMeansResult");
        String actualBmiResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        String actualMeansResult = driver.findElement(By.id("bmi_means")).getAttribute("value");

        if (actualBmiResult.equals(expectedBmiResult) && actualMeansResult.equals(expectedMeansResult))
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");
        System.out.println();

        bmiResults(driver.findElement(By.id("bmi_result")).getAttribute("value"), driver.findElement(By.id("bmi_means")).getAttribute("value"));
    }
    @Test (description = "Screenshot")
    @Description
    public void Test02() {
        try {
            driver.findElement(By.id("kuku"));
        }
        catch (Exception e) {
            System.out.println("Exception is: "+e);
            saveScreenshot();
//            fail();
        }
    }

    @Step ("BMI Results")
    public void bmiResults(String bmi_result, String bmi_means) {
        System.out.println("Your BMI: "+bmi_result);
        System.out.println("It Means: "+bmi_means);
    }

    public String getData (String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./Configuration.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);        }
        catch(Exception e) {
            System.out.println("Exception in reading XML file: " + e);        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }
}
