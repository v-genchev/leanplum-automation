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
    public WebElement reviewAndPublishButton;

    @FindBy(xpath = "//div[contains(@class, 'campaign-transition')]//button[contains(@class, 'success')]")
    public WebElement publishButton;

    @FindBy(xpath = "//button[contains(@class, 'end-campaign')]")
    public WebElement finishCampaignButton;

    @FindBy(xpath = "//div[contains(@class,'modal')]//button[descendant::span[text()='Finish']]")
    public WebElement endCampaignModalFinishButton;

    @FindBy(xpath = "//span[contains(@class,'badge')]")
    public WebElement campaignStatusBadge;

    @FindBy(xpath = "//div[@class='summary-info']//p[@class='title']")
    public List<WebElement> campaignSummaryHeaders;

    @FindBy (xpath = "//div[@data-testid='modal-spinner']")
    public WebElement spinner;

    @FindBy(xpath = "//span[@class='spinner']")
    public WebElement reviewCampaignItemsSpinner;

    public EditCampaignPage(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(campaignInfo);
    }

    public void navigateToStep(String stepName){
        waitForAllElements(secondaryNavigationItems);
        getElementByText(secondaryNavigationItems, stepName).click();
    }

    public void reviewCampaign(){
        reviewAndPublishButton.click();
    }

    public void publishCampaign(){
        publishButton.click();
        waitForElementToDissapear(publishButton);
        waitForElementToDissapear(spinner);
    }

    public void finishCampaign(){
        finishCampaignButton.click();
        waitForElement(endCampaignModalFinishButton);
        endCampaignModalFinishButton.click();
        waitForElementToDissapear(endCampaignModalFinishButton);
        waitForElementToDissapear(spinner);
    }

    public String getCampaignCurrentStatus(){
        return campaignStatusBadge.getText();
    }

    public List<String> getSummaryHeaders(){
        waitForElementToDissapear(reviewCampaignItemsSpinner);
        return getElementsText(campaignSummaryHeaders);
    }
}
