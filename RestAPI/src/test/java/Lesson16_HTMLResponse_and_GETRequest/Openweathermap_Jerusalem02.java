package Lesson16_HTMLResponse_and_GETRequest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.fail;
import static org.testng.Assert.assertEquals;

public class Openweathermap_Jerusalem02 {

    String city = "Jerusalem,IL";
    String key = "cc923360c30301d5f2fb2da06d8d591f";
    String url = "https://api.openweathermap.org/data/2.5/weather";

    public RequestSpecification httpRequest;
    public Response response;
    private JsonPath jp;
    static WebDriver driver;
    private String humidityAPI;
    private String humidityWeb;

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        response = httpRequest.get("?units=metric&q="+city+"&appid="+key);
        jp = response.jsonPath();
        humidityAPI = jp.get("main.humidity").toString();
    }

    @Test
    public void test01() {
        String country = jp.get("sys.country").toString();
        assertEquals(country, "IL");
    }
    @Test
    public void test02() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://openweathermap.org/");
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement searchRow = driver.findElement(By.xpath("//*[@name='q']"));
        wait.until(ExpectedConditions.elementToBeClickable(searchRow));
        searchRow.sendKeys(city);
        searchRow.sendKeys(Keys.RETURN);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("container"))));
        driver.findElement(By.partialLinkText("Jerusalem, IL")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("current-temp")));
        humidityWeb = driver.findElement(By.xpath("//*[@id=\"weather-widget\"]/div[2]/div[1]/div[1]/div[2]/ul/li[3]")).getText().split("\n")[1];

        try {
            assertEquals(humidityAPI+"%", humidityWeb);
        } catch (AssertionError error) {
            System.out.println(error);
            fail();
        }
        finally {
            driver.quit();
        }
    }

}
