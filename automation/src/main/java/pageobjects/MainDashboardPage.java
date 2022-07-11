package pageobjects;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.components.MessageOverlayComponent;

public class MainDashboardPage extends Page{

    @FindBy(xpath="//div[@class='app-view']")
    public WebElement appView;

    public MainDashboardPage(DynamicDriverManager driverManager) {
        this(driverManager, false);
    }

    public MainDashboardPage(DynamicDriverManager driverManager, boolean closeInitialMessage) {
        super(driverManager);
        if(closeInitialMessage) {
            new MessageOverlayComponent(driverManager).closeMessage();
        }
        waitForPage(appView);
    }
}
