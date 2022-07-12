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

    @FindBy(xpath="//div[@class = 'campaign-info']//div[contains(@class, 'composer-summary')]//button")
    public WebElement reviewAndPublish;

    @FindBy(xpath = "//div[contains(@class, 'campaign-transition')]//button[contains(@class, 'success')]")
    public WebElement publishButton;

    @FindBy(xpath = "//div[contains(@class, 'content-replacing-spinner')]")
    public WebElement spinner;

    public EditCampaignPage(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(campaignInfo);
    }

    public void navigateToStep(String stepName){
        waitForAllElements(secondaryNavigationItems);
        getElementByText(secondaryNavigationItems, stepName).click();
    }

    public void reviewCampaign(){
        reviewAndPublish.click();
    }

    public void publishCampaign(){
        publishButton.click();
        waitForElementToDissapear(publishButton);
        waitForElementToDissapear(spinner);
    }
}
