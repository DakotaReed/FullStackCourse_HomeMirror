package Lesson18__TestNG_Advanced;

import org.testng.annotations.Test;

public class MyTestNG2 {

    @Test (groups = "regression")
    public void test01() {
        System.out.println("TNG2 - Test01");
    }
    @Test (groups = "sanity")
    public void test02() {
        System.out.println("TNG2 - Test02");
    }
    @Test (groups = "regression")
    public void test03() {
        System.out.println("TNG2 - Test03");
    }

}
