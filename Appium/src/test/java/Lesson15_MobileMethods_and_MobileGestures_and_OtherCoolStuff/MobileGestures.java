package Lesson15_MobileMethods_and_MobileGestures_and_OtherCoolStuff;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class MobileGestures {

    private String reportDirectory = "C:\\Automation\\FullStackCourse_HomeMirror\\Appium\\AppiumStudioReports\\";
    private String reportFormat = "xml";
    private String testName = "MobileGestures.html";
    protected AndroidDriver<AndroidElement> driver = null;

    protected static DesiredCapabilities dc;

    protected static TouchAction action;

    protected static SoftAssert softAssert;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc = new DesiredCapabilities();
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "1A221FDF6003DK");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        action = new TouchAction(driver);
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void closeSession() {
//        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.quit();
    }



    @Test(description = "Test 01: Verify Displayed Time")
    @Description("Verify Displayed Time")
    public void test01_verifyDisplayedTime() {
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        driver.findElement(By.xpath("//*[@text='Date Widgets']")).click();
        driver.findElement(By.xpath("//*[@text='2. Inline']")).click();
        action.press(new ElementOption()
                .withElement(driver.findElement(By.xpath("//*[@contentDescription='12']"))))
                .moveTo(new ElementOption().withElement(driver.findElement(By.xpath("//*[@contentDescription='9']"))))
                .release().perform();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        action.press(new ElementOption()
                        .withElement(driver.findElement(By.xpath("//*[@contentDescription='15']"))))
                .moveTo(new ElementOption().withElement(driver.findElement(By.xpath("//*[@contentDescription='45']"))))
                .release().perform();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        softAssert.assertEquals(driver.findElement(By.id("hours")).getText(), "9");
        softAssert.assertEquals(driver.findElement(By.id("minutes")).getText(), "45");
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("Displayed Time is NOT 9:45");
            fail();
        }

        driver.launchApp();
//        driver.findElement(By.id("continue_button")).click();
    }

    @Test(description = "Test 02: Verify Sample Menu is Displayed")
    @Description("Verify Sample Menu is Displayed")
    public void test02_verifySampleMenuDisplayed() {
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        driver.findElement(By.xpath("//*[@contentDescription='Expandable Lists']")).click();
        driver.findElement(By.xpath("//*[@contentDescription='1. Custom Adapter']")).click();
        action.longPress(new LongPressOptions()
                .withElement(ElementOption.element(driver.findElement(By.xpath("//*[@text='People Names']"))))
                .withDuration(Duration.ofSeconds(2))).perform();
        try {
            assertTrue(driver.findElement(By.xpath("//*[@text='Sample menu']")).isDisplayed());
        } catch (AssertionError e) {
            System.out.println("Sample Menu is NOT Displayed");
            fail();
        }

        driver.launchApp();
    }

    @Test(description = "Test 03: Verify Last of 'Views' is 'WebView'")
    @Description("Verify Last of 'Views' is 'WebView'")
    public void test03_verifyLastViewIsWebView() {
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        swipeScreen(Direction.UP);
        swipeScreen(Direction.UP);
        swipeScreen(Direction.UP);
        List<AndroidElement> viewOptions = driver.findElements(By.xpath("//*[@resource-id='android:id/text1']"));
        try {
            assertEquals(viewOptions.get(viewOptions.size() - 1).getText(), "WebView");
        } catch (AssertionError e) {
            System.out.println("Last of 'Views' is NOT 'WebView', is: " + viewOptions.get(viewOptions.size() - 1).getText());
        }
    }



    public void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

}
