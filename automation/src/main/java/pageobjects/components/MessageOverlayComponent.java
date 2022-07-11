package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

public class MessageOverlayComponent extends Component {

    @FindBy(xpath="//iframe[contains(@id,'message')]")
    public WebElement mainFrame;

    @FindBy(xpath="//div[@id='buttonsLayer']//div[@id='button-3']")
    public WebElement closeButton;

    public MessageOverlayComponent(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(mainFrame);
        driverManager.switchToFrame(mainFrame);
    }

    public void closeMessage(){
        waitForElementToBeClickable(closeButton);
        closeButton.click();
        driverManager.switchToDefaultContent();
    }
}
