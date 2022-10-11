package Lesson06__TestNG;

import org.testng.annotations.*;

public class TestNG_Example {

    @BeforeClass
    public void startSession() {
        System.out.println("Before Class");
    }
    @AfterClass
    public void closeSession() {
        System.out.println("After Class");
    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @Test
    public void test01() {
        System.out.println("test01");
    }
    @Test
    public void test02() {
        System.out.println("test02");
    }
    @Test (priority = 0, enabled = false)
    public void test04() {
        System.out.println("test04");
    }
    @Test (dependsOnMethods = {"test01", "test02"})
    public void test03() {
        System.out.println("test03");
    }

}
