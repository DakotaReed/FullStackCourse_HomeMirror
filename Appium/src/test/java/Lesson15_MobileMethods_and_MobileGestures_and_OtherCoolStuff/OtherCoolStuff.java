package Lesson15_MobileMethods_and_MobileGestures_and_OtherCoolStuff;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class OtherCoolStuff {

    private String reportDirectory = "E:\\Automation\\FullStackCourse_HomeMirror\\Appium\\AppiumStudioReports\\";
    private String reportFormat = "xml";
    private String testName = "OtherCoolStuff.html";
    protected AndroidDriver<AndroidElement> driver = null;

    protected static DesiredCapabilities dc;

    protected static TouchAction action;

    protected static SoftAssert softAssert;

    protected static List<AndroidElement> list;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc = new DesiredCapabilities();
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "1A221FDF6003DK");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        dc.setCapability("appWaitDuration", 10000);

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        action = new TouchAction(driver);
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void closeSession() {
//        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.quit();
    }

    @Test(description = "Test 01: Verify 'Media' Element")
    public void test01_verifyMediaElement() {
        driver.executeScript("seetest:client.verifyElementFound(\"NATIVE\", \"xpath=//*[@text='Media']\",0)");
    }

    @Test(description = "Test 02: Verify 'Media' Element")
    public void test02_verifyMediaElement() {
//                   driver.startActivity(new Activity("com.experitest.ExperiBank", "com.experitest.ExperiBank.LoginActivity"));
        driver.executeScript("seetest:client.launch(\"com.experitest.ExperiBank/.LoginActivity\", \"false\", \"true\")");
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        assertEquals(driver.findElement(By.partialLinkText("Your balance is")).getText(), "Your balance is: 100.00$");
    }

//---------adb shell am start -n com.experitest.ExperiBank/com.experitest.ExperiBank.LoginActivity
}
