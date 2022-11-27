package Lesson14_ExecuteAppium;
import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import static org.testng.Assert.assertTrue;

public class EriBank_Lesson01_FirstTest_Manual {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "EriBank_Lesson01_FirstTest_Manual";
    protected AndroidDriver<AndroidElement> driver = null;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "1A221FDF6003DK");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }


    @Test
    public void testEriBank_Lesson01_FirstTest_Manual() {
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
        driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("0545548047");
        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Dakota Reed");
        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("0.01");
        driver.findElement(By.xpath("//*[@id='countryButton']")).click();
        driver.findElement(By.xpath("//*[@text='Switzerland']")).click();
        driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='button2']")).click();
        driver.findElement(By.xpath("//*[@id='cancelButton']")).click();
        driver.findElement(By.xpath("//*[@id='logoutButton']")).click();
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id='mortageRequestButton']")).isEnabled());
        assertTrue(driver.findElement(By.xpath("//*[@id='expenseReportButton']")).isEnabled());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
