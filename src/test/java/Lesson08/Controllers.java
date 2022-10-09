package Lesson08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Controllers {

    WebDriver driver;
    @BeforeClass
    public void enter() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_controllers.html");       }
    @AfterClass
    public void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();    }

    @Test
    public void Test01__Exercise01() throws InterruptedException {
        driver.findElement(By.name("firstname")).sendKeys("Dakota");
        Thread.sleep(1000);
        driver.findElement(By.name("lastname")).sendKeys("Reed");
        Thread.sleep(1000);
        Select continents = new Select(driver.findElement(By.id("continents")));
//        List<WebElement> listOfContinents = continents.getOptions();
        continents.selectByValue("austria");
        Thread.sleep(2000);
        driver.findElement(By.id("submit")).click();
        if (driver.getCurrentUrl().contains("Dakota") && driver.getCurrentUrl().contains("Reed"))
            System.out.println("Test Passed");
        else
            System.out.println("Test Failed");
    }
    @Test
    public void Test02__Exercise02() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().back();
        driver.navigate().refresh();
        driver.findElement(By.name("firstname")).sendKeys("Dakota");
        driver.findElement(By.name("lastname")).sendKeys("Reed");
        driver.findElement(By.id("sex-1")).click();
        driver.findElement(By.name("datepicker")).click();
        driver.findElement(By.cssSelector(".ui-datepicker-prev.ui-corner-all")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[4]/a")).click();
        driver.findElement(By.id("profession-1")).click();
        driver.findElement(By.id("photo")).sendKeys("C://Users/Dakota/Pictures/DR.bmp");
        driver.findElement(By.id("tool-2")).click();
        Select continents = new Select(driver.findElement(By.id("continents")));
        continents.selectByValue("austria");
        Select seleniumCommands = new Select(driver.findElement(By.id("selenium_commands")));
        seleniumCommands.selectByVisibleText("WebElement Commands");
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();

        if (driver.getCurrentUrl().contains("datepicker")) {
        String[] arrayUrl = driver.getCurrentUrl().split("&");
        for (int i=0; i<arrayUrl.length; i++) {
            if (arrayUrl[i].contains("datepicker")) {
                String[] firstDataArray = arrayUrl[i].split("=");
                String[] dataArray = firstDataArray[1].split("%");
                for (int j = 0; j < 3; j++) {
                    if (dataArray[j].contains("2F"))
                        dataArray[j] = dataArray[j].replace("2F", "");
                }

                System.out.println(dataArray[2] + "-" + dataArray[0] + "-" + dataArray[1]);
            }
        }
        }
        else
            System.out.println("URL is not correct.");
//        String[] arr = driver.getCurrentUrl().split("%");
//        String day = arr[1].substring(arr[1].length() - 2);
//        String month = arr[0].substring(arr[0].length() - 2);
//        String year = arr[2].substring(2,6);
//        System.out.println(year + "-" + month + "-" + day);
    }
}
