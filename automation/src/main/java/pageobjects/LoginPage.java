package pageobjects;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(id = "login-page")
    public WebElement component;

    @FindBy(xpath = "//form[@name='signInForm']//input[@name='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//form[@name='signInForm']//input[@name='password']")
    public WebElement passwordInput;

    @FindBy(id = "remember")
    public WebElement rememberCheckbox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    public LoginPage(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(component);
    }

    public void login(String email, String password) {
        login(email, password, false);
    }

    public void login(String email, String password, boolean remember) {
        typeWithClear(emailInput, email);
        typeWithClear(passwordInput, password);
        checkboxAction(rememberCheckbox, remember);
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

}
