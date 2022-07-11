package pageobjects;

import drivermanager.DynamicDriverManager;
import helpers.PropertiesCache;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(xpath = "//body[contains(@class, 'home page')]")
    public WebElement mainContent;

    public HomePage(DynamicDriverManager driverManager) {
        this(driverManager, false);
    }

    public HomePage(DynamicDriverManager driverManager, boolean navigateToUrl) {
        super(driverManager);
        if (navigateToUrl) {
            driverManager.getDriver().navigate().to(PropertiesCache.getInstance().getProperty("base.url"));
            waitForPage(mainContent);
        }
    }

}
