package Objects__PageObjectsPattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPage {

    @FindBy(id = "occupation")
    private WebElement txt_occupation;

    @FindBy(id = "age")
    private WebElement txt_age;

    @FindBy(id = "location")
    private WebElement txt_location;

    @FindBy(xpath = "//a/input")
    private WebElement btn_click;

    public void fiLLFormAction(String occupation, String age, String location) {
        txt_occupation.sendKeys(occupation);
        txt_age.sendKeys(age);
        txt_location.sendKeys(location);
        btn_click.click();
    }

}
