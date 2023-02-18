package doubleExamples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class EBayLogo_D {
    Document docD;

    @BeforeMethod
    public void start()  throws IOException {
        docD = Jsoup.connect("https://www.ebay.com/").get();
    }

    @Test
    public void test01() {
        Element logoD = docD.getElementsByAttributeValue("alt", "eBay Logo").get(0);
        assertTrue(logoD.attr("width").equals("250"));
        System.out.println(logoD.attr("width"));
    }

    @Test
    public void test02() {
        Element logoD = docD.getElementsByAttributeValue("alt", "eBay Logo").get(0);
        assertTrue(logoD.attr("height").equals("200"));
        System.out.println(logoD.attr("height"));
    }

    @Test
    public void test03() {
        Element logoD = docD.getElementsByAttributeValue("aria-label", "Select a category for search").get(0);
        assertTrue(logoD.text().equals("All Categories"));
        System.out.println(logoD.text());
    }

}
