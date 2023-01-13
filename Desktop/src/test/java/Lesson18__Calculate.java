import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;

public class Lesson18__Calculate {

    private WindowsDriver driver;
    private DesiredCapabilities capabilities;
    private final String calcApp = "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";

    @BeforeClass
    public void setup() throws IOException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", calcApp);
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void Clear() {
        driver.findElement(By.xpath("//*[@AutomationId='clearButton']")).click();
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

    @Test
    public void Addition() {
        driver.findElement(By.name("One")).click();
        driver.findElement(By.name("Plus")).click();
        driver.findElement(By.name("Seven")).click();
        driver.findElement(By.name("Equals")).click();
    }

    @Test
    public void Combination() {
        driver.findElement(By.name("Seven")).click();
        driver.findElement(By.name("Multiply by")).click();
        driver.findElement(By.name("Nine")).click();
        driver.findElement(By.name("Plus")).click();
        driver.findElement(By.name("One")).click();
        driver.findElement(By.name("Equals")).click();
        driver.findElement(By.name("Divide by")).click();
        driver.findElement(By.name("Eight")).click();
        driver.findElement(By.name("Equals")).click();
        assertEquals("8", getCalculatorResultText());
    }

    @Test
    public void Division() {
        driver.findElement(By.name("Eight")).click();
        driver.findElement(By.name("Eight")).click();
        driver.findElement(By.name("Divide by")).click();
        driver.findElement(By.name("One")).click();
        driver.findElement(By.name("One")).click();
        driver.findElement(By.name("Equals")).click();
        assertEquals("8", getCalculatorResultText());
    }

    @Test
    public void Multiplication() {
        driver.findElement(By.name("Nine")).click();
        driver.findElement(By.name("Multiply by")).click();
        driver.findElement(By.name("Nine")).click();
        driver.findElement(By.name("Equals")).click();
        assertEquals("81", getCalculatorResultText());
    }

    @Test
    public void Subtraction() {
        driver.findElement(By.name("Nine")).click();
        driver.findElement(By.name("Minus")).click();
        driver.findElement(By.name("One")).click();
        driver.findElement(By.name("Equals")).click();
        assertEquals("8", getCalculatorResultText());
    }

    public String getCalculatorResultText() {
        // trim extra text and whitespace off of the display value
        return driver.findElement(By.xpath("//*[@AutomationId='CalculatorResults']")).getText().replace("Display is", "").trim();
    }
}
