package Lesson18__TestNG_Advanced;

import org.testng.annotations.Test;

import java.lang.annotation.Annotation;

public class MyTestNG2 implements ExecutionData{

    @Test (groups = "regression")
    public void test01() {
        System.out.println("TNG2 - Test01");
    }
    @ExecutionData(name = Testers.DAKOTA, category = Category.SANITY, company = "Thanks to ATID College")
    @Test (groups = "sanity")
    public void test02() {
        System.out.println("TNG2 - Test02");
    }
    @Test (groups = "regression")
    public void test03() {
        System.out.println("TNG2 - Test03");
    }

    @Override
    public Testers name() {
        return null;
    }

    @Override
    public Category category() {
        return null;
    }

    @Override
    public String company() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
