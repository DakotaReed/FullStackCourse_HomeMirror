package Lesson16_HTMLResponse_and_GETRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;


public class EBay {

    Document doc;

    @BeforeClass
    public void startSession() throws IOException {
        doc = Jsoup.connect("https://www.ebay.com/").get();
    }

    @Test
    public void test01()  {
        assertEquals(doc.getElementById("gh-logo").attr("width"), "250");
    }

    @Test
    public void test02()  {
        assertEquals(doc.getElementById("gh-logo").attr("height"), "200");
    }

    @Test
    public void test03()  {
//        assertEquals(doc.getElementsByAttributeValue("id", "gh-cat").get(0).text(), "All Categories");
        assertEquals(doc.getElementById("gh-cat").text(), "All Categories");
    }
}
