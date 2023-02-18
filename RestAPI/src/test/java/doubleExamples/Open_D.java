package doubleExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Open_D {
        String city = "Jerusalem,IL";
        String key = "cc923360c30301d5f2fb2da06d8d591f";
        String unit = "metric";
        String url = "https://api.openweathermap.org/data/2.5/weather";
        String humidityAPI;
        String humidityWEB;

        private RequestSpecification httpRequest;
        private Response response;
        private JsonPath jp;
        private WebDriver driver;
        private Actions action;
        private WebDriverWait wait;

        @BeforeClass
        void startSession() {
            RestAssured.baseURI = url;
            httpRequest = RestAssured.given();
            response = httpRequest.get("?units="+unit+"&q="+city+"&appid="+key);
            jp = response.jsonPath();
        }

        @Test
        void test01() {
            assertEquals(jp.get("sys.country").toString(), "IL");
            System.out.println(jp.get("sys.country").toString());
        }

        @Test
        void test02() {
            humidityAPI = jp.get("main.humidity").toString();
            System.out.println(humidityAPI);
        }

        @Test
        void test03() throws InterruptedException {
            humidityAPI = jp.get("main.humidity").toString();
            System.out.println(humidityAPI);

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://openweathermap.org/");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            action = new Actions(driver);
            driver.findElement(By.cssSelector("input[placeholder='Weather in your city']")).sendKeys(city);
            driver.findElement(By.cssSelector("input[placeholder='Weather in your city']")).sendKeys(Keys.RETURN);
            driver.findElement(By.xpath("//tbody/tr/td[2]/b/a")).click();
            System.out.println(humidityAPI);
            humidityWEB = driver.findElements(By.cssSelector("ul[class='weather-items text-container orange-side standard-padding']>li")).get(2).getText().toString().split("\n")[1].replace("%", "");
            System.out.println(humidityWEB);
            driver.quit();
            assertTrue(humidityAPI.equals(humidityWEB));
        }

}
