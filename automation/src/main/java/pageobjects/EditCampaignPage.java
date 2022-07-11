package pageobjects;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditCampaignPage extends Page{

    @FindBy(xpath="//div[@class = 'campaign-info']")
    public WebElement campaignInfo;

    @FindBy(xpath="//div[contains(@class, 'navigation-bar')]//li[contains(@class, 'stepper-step')]")
    public List<WebElement> secondaryNavigationItems;

    public EditCampaignPage(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(campaignInfo);
    }

    public void navigateToStep(String stepName){
        getElementByText(secondaryNavigationItems, stepName).click();
    }
}
