package pageobjects;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CampaignsPage extends Page{

    @FindBy(id = "buttonCreateCampaign")
    public WebElement createCampaignHeaderButton;

    public CampaignsPage(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(createCampaignHeaderButton);
    }

    public void startCampaignCreation(){
        waitForElementToBeClickable(createCampaignHeaderButton);
        createCampaignHeaderButton.click();
    }

}
