package Lesson06__TestNG;//
//
//<dependencies>
//<dependency>
//<groupId>io.qameta.allure</groupId>
//<artifactId>allure-testng</artifactId>
//<version>2.17.2</version>
//</dependency>
//<dependency>
//<groupId>org.aspectj</groupId>
//<artifactId>aspectjweaver</artifactId>
//<version>1.9.2</version>
//</dependency>
//</dependencies>
//
//<properties>
//<aspectj.version>1.9.2</aspectj.version>
//<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
//</properties>
//
//<build>
//<plugins>
//<plugin>
//<groupId>org.apache.maven.plugins</groupId>
//<artifactId>maven-surefire-plugin</artifactId>
//<version>2.22.1</version>
//<configuration>
//<argLine>
//                    -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
//</argLine>
//</configuration>
//</plugin>
//</plugins>
//</build>
//

//allure serve allure-results


import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

public class Pattern__Allure {

//    @Attachment(value = "Page Screen-Shot", type = "image/png")
//    public byte[] saveScreenshot() {
//        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//    }

    @Test (description = "First Test of Test")
    @Description ("Test Description is: First Try.")
    public void Test() {}

    @Step ("First Test Body Element")
    public void step() {}


}
