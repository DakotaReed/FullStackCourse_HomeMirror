package Objects__PageObjectsPattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClickPage {

    @FindBy(tagName = "button")
    private WebElement btn_button;

    public String gettingTextButton() {
        return btn_button.getText();
    }

}
