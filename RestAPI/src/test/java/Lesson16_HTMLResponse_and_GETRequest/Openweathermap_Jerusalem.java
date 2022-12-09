package Lesson16_HTMLResponse_and_GETRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Openweathermap_Jerusalem {

    String city = "Jerusalem,IL";
    String key = "cc923360c30301d5f2fb2da06d8d591f";
    String url = "https://api.openweathermap.org/data/2.5/weather";

    public RequestSpecification httpRequest;
    public Response response;

    @BeforeClass
    public void startSession() {
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
    }

    @BeforeMethod
    public void beforeMethod() {
        response = httpRequest.get("?units=metric&q="+city+"&appid="+key);
    }

    @Test
    public void test01() {
        //        response.prettyPrint();
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("Date"));
        assertTrue(response.getContentType().contains("json")&&!response.getContentType().contains("xml"));
    }
    @Test
    public void test02() {
        assertTrue(response.getStatusCode()==200);
    }
}
