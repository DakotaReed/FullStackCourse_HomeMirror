package Lesson18__TestNG_Advanced;

import org.testng.annotations.Test;

public class MyTestNG1 {

    @Test (groups = "sanity")
    public void test01() {
        System.out.println("TNG1 - Test01");
    }
    @Test (groups = "regression")
    public void test02() {
        System.out.println("TNG1 - Test02");
    }
    @Test (groups = "sanity")
    public void test03() {
        System.out.println("TNG1 - Test03");
    }

}
