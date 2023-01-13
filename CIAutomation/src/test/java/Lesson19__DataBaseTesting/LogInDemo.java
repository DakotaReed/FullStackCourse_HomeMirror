package Lesson19__DataBaseTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class LogInDemo {

    String dbUrl = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7586361";
    String username = "sql7586361";
    String password = "CVgAL7X9iz";
    String query = "select * from Test where comments = 'correct'";
    String user_name, user_password;
    Connection con;
    Statement stmt;
    ResultSet rs;
    WebDriver driver;

    public void getUserNamePassword() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, username, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error Occurred while Connecting to DB, See Details: " + e);
        }
        while (rs.next()) {
            user_name = rs.getString(2);
            user_password = rs.getString(3);
            System.out.println(user_name+" ... "+user_password);
        }

    }

    @BeforeClass
    public void start() throws SQLException {
        getUserNamePassword();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://atidcollege.co.il/Xamples/demo");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test01_verifyLogIn() {
        driver.findElement(By.name("username")).sendKeys(user_name);
        driver.findElement(By.name("password")).sendKeys(user_password);
        driver.findElement(By.id("submit")).click();
        assertEquals(driver.findElement(By.id("loggedin")).getText(), "You Are Logged in");
    }

    @AfterClass
    public void close() throws SQLException {
        driver.quit();
        con.close();
    }

}
