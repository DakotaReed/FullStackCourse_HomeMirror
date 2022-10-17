package Objects__PageObjectsPattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(name = "username2")
    private WebElement txt_user;

    @FindBy(name = "password2")
    private WebElement txt_password;

    @FindBy(id = "submit")
    private WebElement btn_submit;

    public void logInAction(String userName, String password) {
        txt_user.sendKeys(userName);
        txt_password.sendKeys(password);
        btn_submit.click();
    }

}
