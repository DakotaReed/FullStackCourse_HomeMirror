package Lesson11__ToDo;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@Listeners(ListenersAuto.class)

public class ToDo {

    static WebDriver driver;
    Actions actionsToDo;
    String textTask;
    String newNameTask;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getData("UrlToDo"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        actionsToDo = new Actions(driver);
    }
    @AfterClass
    public void closeSession() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test(description = "Creating Task")
    public void Test01__creatingTask() throws InterruptedException {
        textTask = getData("FirstTask");
        inputTask(textTask);
        taskCreated();
        Thread.sleep(1000);
    }
    @Test(description = "Deleting Task")
    public void Test02__deletingTaskOff() throws InterruptedException {
        deletingTask();
    }
    @Test(description = "Change Name of Task")
    public void Test03__changeName() throws InterruptedException {
        newNameTask = getData("SecondTask");
        inputTask(textTask);
        Thread.sleep(1000);
        changingName(textTask, newNameTask);
    }
    @Test(description = "Task Completed")
    public void Test04__completedTask() throws InterruptedException {
        completingTask();
        Thread.sleep(1000);
    }
    @Test(description = "Filtering")
    public void Test05__filtering() throws InterruptedException {
        inputTask(textTask);
        Thread.sleep(2000);
        active();
        Thread.sleep(2000);
        completed();
        Thread.sleep(2000);
        all();
        Thread.sleep(2000);
    }
    @Test(description = "Clear Completed")
    public void Test06__clearCompleted() throws InterruptedException {
        clearingCompleted();
    }

    @Step("Input Task")
    public void inputTask(String textTask) {
        driver.findElement(By.cssSelector("input[class='new-todo']")).sendKeys(textTask + Keys.ENTER);
    }
    @Step("Task Created")
    public void taskCreated() {
        assertTrue(driver.findElement(By.xpath("/html/body/section/div/section/ul/li/div/label")).getText().equals(textTask));
    }
    @Step("Detection and Click -- Deletion Element")
    public void deletingTask() {
        actionsToDo.moveToElement(driver.findElement(By.className("todo-list"))).build().perform();
        assertTrue(driver.findElement(By.className("destroy")).isDisplayed());
        driver.findElement(By.className("destroy")).click();
        try {
            driver.findElement(By.xpath("/html/body/section/div")).findElement(By.tagName("section"));
            System.out.println("Task don't deleted !");
        }
        catch (Exception e) {        }
    }
    @Step("Change Name")
    public void changingName(String oldName, String newName) throws InterruptedException {
        actionsToDo.doubleClick(driver.findElement(By.cssSelector("div[class='view']"))).build().perform();
        Thread.sleep(1000);
        actionsToDo.clickAndHold(driver.findElement(By.cssSelector("li[class='editing']"))).moveToElement(driver.findElement(By.xpath("/html/body/aside/header"))).build().perform();
        actionsToDo.doubleClick(driver.findElement(By.cssSelector("li[class='editing']"))).sendKeys(Keys.DELETE).sendKeys(newName).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(1000);
        assertTrue(driver.findElement(By.xpath("/html/body/section/div/section/ul/li/div/label")).getText().equals(newName));
    }
    @Step("Completing Task")
    public void completingTask() {
        driver.findElement(By.cssSelector("input[class='toggle']")).click();
        assertTrue(driver.findElement(By.cssSelector("li[class='completed']")).isEnabled());
    }
    @Step("Filtering Active")
    public void active() {
        driver.findElement(By.linkText("Active")).click();
        assertTrue(driver.findElement(By.linkText("Active")).getAttribute("class").equals("selected"));
    }
    @Step("Filtering Completed")
    public void completed() {
        driver.findElement(By.linkText("Completed")).click();
        assertTrue(driver.findElement(By.linkText("Completed")).getAttribute("class").equals("selected"));
    }
    @Step("Filtering All")
    public void all() {
        driver.findElement(By.linkText("All")).click();
        assertTrue(driver.findElement(By.linkText("All")).getAttribute("class").equals("selected"));
    }
    @Step("Clearing Completed")
    public void clearingCompleted() throws InterruptedException {
        driver.findElement(By.className("clear-completed")).click();
        Thread.sleep(2000);
        List<WebElement> listOfTasks = driver.findElements(By.xpath("/html/body/section/div/section/ul/li"));
        for (WebElement value : listOfTasks)
            assertFalse(value.getAttribute("class").equals("completed"));
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
