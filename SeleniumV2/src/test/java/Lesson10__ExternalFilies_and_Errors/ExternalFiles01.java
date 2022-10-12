package Lesson10__ExternalFilies_and_Errors;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;

public class ExternalFiles01 {

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

    @Test
    public void Test01() {
        driver.findElement(By.id("weight")).sendKeys(getData("Weight"));
        driver.findElement(By.id("hight")).sendKeys(getData("Height"));
        driver.findElement(By.id("calculate_data")).click();

        String expectedBmiResult = getData("ExpectedResultBMI");
        String expectedMeansResult = getData("ExpectedMeansResult");
        String actualBmiResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        String actualMeansResult = driver.findElement(By.id("bmi_means")).getAttribute("value");

        if (actualBmiResult.equals(expectedBmiResult) && actualMeansResult.equals(expectedMeansResult))
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");
        System.out.println();
    }

    public String getData (String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("Configuration.xml");
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
