package Lesson18__TestNG_Advanced;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecutionData {

    public Testers name();
    public Category category();
    public String company();

    public enum Testers {
        DAKOTA,
        DANA,
        MOSHE,
        DAVID
    }

    public enum Category {
        SANITY,
        REGRESSION,
        HEALTHCHECK
    }

}
