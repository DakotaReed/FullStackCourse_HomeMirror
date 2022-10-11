package Lesson08__Controllers_and_AssertsVerifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class Loudev__Support {
    public static String numberOfElem;
    public static void verifyElements(WebDriver driverOut) {
        WebElement leftTable = driverOut.findElement(By.className("ms-container"));
        List<WebElement> list01ofElements = leftTable.findElements(By.className("ms-elem-selectable"));
        for (int i=0; i< list01ofElements.size(); i++) {
            if (list01ofElements.get(i).isDisplayed()) {
                numberOfElem = Integer.toString(i+1);
                assertTrue(list01ofElements.get(i).findElement(By.tagName("span")).getText().equals("elem " + numberOfElem));}
        }
    }
}
