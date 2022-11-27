package Lesson15_MobileMethods_and_MobileGestures_and_OtherCoolStuff;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class MobileMethods_apiDemos {

    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "MobileMethods_apiDemos";
    protected AndroidDriver<AndroidElement> driver = null;

    protected static List<AndroidElement> listOptions;

    protected static List<String> listForTestAmount = new ArrayList<>();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "1A221FDF6003DK");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(description = "Test 01: Verify Amount of Options")
    @Description("Verify Amount of Options")
    public void test01_verifyAmountOptions() {
        listOptions = driver.findElements(By.id("text1"));
        try {
            assertEquals(listOptions.size(), 11);
        } catch (AssertionError e) {
            System.out.println("Actual Amount is: "+listOptions.size());
            fail();
        }
    }

    @Test(description = "Test 02: Verify EriBank Installed")
    @Description("Verify EriBank Installed")
    public void test02_verifyEriBankInstalled() {
        listOptions = driver.findElements(By.id("text1"));
        for (AndroidElement option : listOptions) {
            if (option.getText().equals("Content")) {
                System.out.println("Width of Element 'Content' is:    "+option.getRect().getWidth());
                System.out.println("Height of Element 'Content' is:    "+option.getRect().getHeight());
                System.out.println("X_Axis of Element 'Content' is:    "+option.getRect().getX());
                System.out.println("Y_Axis of Element 'Content' is:    "+option.getRect().getY());
                System.out.println("------------------");
            }
        }
        System.out.println("Current Activity is:    "+driver.currentActivity());
        System.out.println("Device Date and Time is:    "+driver.getDeviceTime());
        try {
            assertTrue(driver.isAppInstalled("com.experitest.ExperiBank"));
        } catch (AssertionError e) {
            System.out.println("EriBank don't installed");
            fail();
        }
    }

    @Test(description = "Test 03: Verify Screen Orientation")
    @Description("Verify Screen Orientation")
    public void test03_verifyScreenOrientation() {
        try {
            assertTrue(driver.getOrientation().equals("Landscape"));
        } catch (AssertionError e) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            driver.rotate(ScreenOrientation.PORTRAIT);
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        }
    }

    @Test(description = "Test 04: Verify Device Not Locked")
    @Description("Verify Device Not Locked")
    public void test04_verifyDeviceNotLocked() throws IOException  {
        driver.openNotifications();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Automation\\Files\\Images\\Appium\\Screenshots\\test04__VerifyScreenOrientation_img01.png"));
        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        File scrFile01 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile01, new File("C:\\Automation\\Files\\Images\\Appium\\Screenshots\\test04__VerifyScreenOrientation_img02.png"));

        if (driver.isDeviceLocked())
        {}
        else {
            driver.lockDevice();
            Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
            try {
                assertTrue(driver.isDeviceLocked());
            } catch (AssertionError e) {
                System.out.println("Device STILL NOT Locked");
                fail();
            }
            Uninterruptibles.sleepUninterruptibly(3000, TimeUnit.MILLISECONDS);
            driver.unlockDevice();
            Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
            try {
                assertFalse(driver.isDeviceLocked());
            } catch (AssertionError e) {
                System.out.println("Device STILL Locked");
                fail();
            }
        }
    }

    @Test(description = "Test 05: Verify Amount of Word 'ListView' in Page Source")
    @Description("Verify Amount of Word 'ListView' in Page Source")
    public void test03_verifyAmountListViewInPageSource() {
        String[] pageSource = driver.getPageSource().split(" ");
        String listSize;
        for (String word : pageSource) {
            if (word.contains("ListView"))
                listForTestAmount.add("+");
        }
        try {
            listSize = Integer.toString(listForTestAmount.size());
        } catch (NullPointerException e) {
            listSize = "0";
        }
        try {
            assertEquals(Integer.parseInt(listSize), 4);
        } catch (AssertionError e) {
            System.out.println("Word 'ListView' NOT equals in Page Source 4 times. Actual Amount is: "+listSize);
        }
    }


    @AfterClass
    public void closeSession() {
//        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.quit();
    }

}
