package Lesson11__ReportingSystem_and_Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

@Listeners(ListenersAuto.class)

public class Actions01 {

    WebDriver driver;
    Actions actionsDrag;
    String actualText;
    String expectedText;
    String nameOfElement01;
    String nameOfElement02;
    String textDemo;
    WebElement element;
    String backgroundColor;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getData("UrlActions"));
        actionsDrag = new Actions(driver);
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
    @Attachment(value = "Page Screen-Shot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test (description = "Drag&Drop")
    @Description
    public void Test01() {
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        actionsDrag.dragAndDrop(draggable,droppable).build().perform();
        actualText = driver.findElement(By.id("droppable")).findElement(By.tagName("p")).getText();
        expectedText = getData("ExpectedText");
        dropped(actualText, expectedText);
    }
    @Test (description = "Ctrl + click")
    @Description
    public void Test02() {
        List<WebElement> listItems = driver.findElements(By.xpath("//div[@id='contact_info_left']/ol/li"));
        actionsDrag.clickAndHold(listItems.get(1)).clickAndHold(listItems.get(2)).build().perform();
        nameOfElement01 = listItems.get(1).getText();
        nameOfElement02 = listItems.get(2).getText();
        selected(nameOfElement01, nameOfElement02);
    }
    @Test (description = "Double Click")
    @Description
    public void Test03() {
        actionsDrag.doubleClick(driver.findElement(By.id("dbl_click"))).build().perform();
        textDemo = driver.findElement(By.id("demo")).getText();
        displayed(textDemo);
    }
    @Test (description = "Mouse Move")
    @Description
    public void Test04() throws InterruptedException {
        actionsDrag.moveToElement(driver.findElement(By.id("mouse_hover"))).build().perform();
//        actionsDrag.moveToElement(driver.findElement(By.id("dbl_click"))).click().build().perform();
        Thread.sleep(2000);
        painted();
    }
    @Test (description = "Scrolling")
    @Description
    public void Test05() {
        WebElement textAfterScrolling = driver.findElement(By.id("scrolled_element"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textAfterScrolling);
        scrolled();
    }

    @Step("Dropped")
    public void dropped(String actualText, String expectedText) {
        try {
            assertTrue(actualText.equals(expectedText));        }
        catch (AssertionError e) {
            System.out.println(e);
            saveScreenshot();        }
        System.out.println(actualText+"  <----is actual text.");
        System.out.println();
    }
    @Step("Selected")
    public void selected(String nameOfElement01, String nameOfElement02) {
        List<String> namesElements = new ArrayList<>();
        namesElements.add(nameOfElement01);
        namesElements.add(nameOfElement02);
        for (String value : namesElements)
            System.out.println(value+"  <----is selected element.");
    }
    @Step("Displayed")
    public void displayed(String hiddenText) {
        assertTrue(driver.findElement(By.id("dbl_click")).isDisplayed());
        System.out.println(hiddenText+"  <----is hidden text.");
        System.out.println();
    }
    @Step("Painted and Mouse on")
    public void painted() {
        try {
            backgroundColor = driver.findElement(By.id("mouse_hover")).getCssValue("background-color");
            assertFalse(backgroundColor.equals(getData("MouseOffColour")));
            System.out.println("The Color is: "+backgroundColor);       }
        catch (AssertionError e) {
            saveScreenshot();
            System.out.println(e+"     Mouse out of Element");        }
        finally {
            System.out.println();
            System.out.println("Mouse off Colour is: "+getData("MouseOffColour"));
        }
    }
    @Step("Scrolled")
    public void scrolled() {
        System.out.println("Page is scrolled");
        System.out.println();
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
