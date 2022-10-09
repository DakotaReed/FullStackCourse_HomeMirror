package Lesson09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SwitchAndNavigation {

    WebDriver driver;
    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_switch_navigation.html");       }
    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }

    @Test
    public void Test01__Alert() throws InterruptedException {
        driver.findElement(By.id("btnAlert")).click();
        Thread.sleep(2000);
        Alert popUp = driver.switchTo().alert();
        System.out.println(popUp.getText());
        popUp.accept();
        assertTrue(driver.findElement(By.id("output")).getText().equals("Alert is gone."));
    }
    @Test
    public void Test02__Prompt() throws InterruptedException {
        System.out.println();
        driver.findElement(By.id("btnPrompt")).click();
        Alert popUp = driver.switchTo().alert();
        System.out.println(popUp.getText());

        popUp.sendKeys("Dakota");
        Thread.sleep(2000);
        popUp.accept();
        assertTrue(driver.findElement(By.id("output")).getText().equals("Dakota"));

    }
    @Test
    public void Test03__ConfirmBox() throws InterruptedException {
        System.out.println();
        driver.findElement(By.id("btnConfirm")).click();
        Thread.sleep(2000);
        Alert popUp = driver.switchTo().alert();
        popUp.dismiss();
        System.out.println(driver.findElement(By.id("output")).getText());
        System.out.println();
    }
    @Test
    public void Test04__iFrame() {
        WebElement iForm = driver.findElement(By.xpath("//*[@id=\"contact_info_left\"]/iframe"));
        driver.switchTo().frame(iForm);
        System.out.println(driver.findElement(By.id("iframe_container")).getText());
        System.out.println();

        String expectedResult = "This is an IFrame !";
        assertEquals(driver.findElement(By.id("iframe_container")).getText(), expectedResult);
        driver.switchTo().parentFrame();
    }
    @Test
    public void Test05__Tab() throws InterruptedException {
        driver.findElement(By.id("btnNewTab")).click();
        String winHandleFirst = driver.getWindowHandle();
//        System.out.println(winHandleFirst); //--------First window

//        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(newTab.get(1));

//        Set<String> winHandleSet = driver.getWindowHandles();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
//            System.out.println(winHandle);
        }
        System.out.println(driver.findElement(By.id("new_tab_container")).getText());
        System.out.println();

        String expectedResult = "This is a new tab";
        assertEquals(driver.findElement(By.id("new_tab_container")).getText(), expectedResult);
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(winHandleFirst);
    }
    @Test
    public void Test06__Window() throws InterruptedException {
        driver.findElement(By.linkText("Open New Window")).click();
        Thread.sleep(2000);
        String winHandleFirst = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles())
            driver.switchTo().window(winHandle);
        driver.manage().window().maximize();
        System.out.println(driver.findElement(By.id("new_window_container")).getText());

        String expectedResult = "This is a new window";
        assertEquals(driver.findElement(By.id("new_window_container")).getText(), expectedResult);
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(winHandleFirst);
    }
}
