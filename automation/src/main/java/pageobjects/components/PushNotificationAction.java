package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PushNotificationAction extends ActionComponent{

    @FindBy(xpath = "//div[contains(@class, 'content-preview')]//div[contains(@class, 'message')]")
    public WebElement contentPreviewMessage;

    public PushNotificationAction(DynamicDriverManager driverManager) {
        super(driverManager);
    }

    public String getContentPreviewMessage(){
        waitForElement(contentPreviewMessage);
        return contentPreviewMessage.getText();
    }
}
