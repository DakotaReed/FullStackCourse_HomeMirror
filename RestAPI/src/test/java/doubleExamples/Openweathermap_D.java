package doubleExamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class Openweathermap_D {
    String city = "Jerusalem,IL";
    String key = "cc923360c30301d5f2fb2da06d8d591f";
    String unit = "metric";
    String url = "https://api.openweathermap.org/data/2.5/weather";

    public RequestSpecification httpRequest;
    public Response response;

    @BeforeClass
    public void startSession() {
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        response = httpRequest.get("?units="+unit+"&q="+city+"&appid="+key);
    }

    @Test
    public void test01() {
        response.prettyPrint();
        System.out.println(response.statusLine());
        System.out.println(response.getHeader("Date"));
    }

    @Test
    public void test02() {
        assertTrue(response.getContentType().contains("json"));
    }

    @Test
    public void test03() {
        assertTrue(response.getStatusCode() == 200);
    }

}
