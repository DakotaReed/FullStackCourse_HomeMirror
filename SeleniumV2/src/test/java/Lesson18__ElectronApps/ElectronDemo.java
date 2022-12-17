package Lesson18__ElectronApps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class ElectronDemo {

    public WebDriver driver;
    public ChromeOptions opt;
    public DesiredCapabilities capabilities;
    public WebDriverWait wait;

    @BeforeClass
    public void startSession() {
        System.setProperty("webdriver.chrome.driver", "E:/Automation/Electron/electrondriver.exe");
        opt = new ChromeOptions();
        opt.setBinary("E:/Automation/Electron/Electron API Demos-win32-ia32/Electron API Demos.exe");
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("chromeOptions", opt);
        capabilities.setBrowserName("chrome");
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @AfterClass
    public void closeSession() {
        driver.findElement(By.id("button-windows")).click();
        driver.quit();
    }

    @Test
    public void test01() {
        driver.findElement(By.id("button-app-sys-information")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sys-info-demo-toggle")));
        driver.findElement(By.id("sys-info-demo-toggle")).click();
        if (driver.findElement(By.id("sys-info")).isDisplayed())
        {}
        else
            driver.findElement(By.id("sys-info-demo-toggle")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("sys-info"))));
        String text = driver.findElement(By.xpath("//*[@id='app-sys-information-section']/div[3]/div/div/p[1]/code")).getText();
//        System.out.println(text);
        assertEquals(text, "os");
    }
//  ctrl+shift+i
}
