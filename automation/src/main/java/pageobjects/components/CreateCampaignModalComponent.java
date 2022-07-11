package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateCampaignModalComponent extends ChooseTemplateComponent {

    @FindBy(xpath = "//div[@class= 'options']//div[contains(@class, 'option')]")
    public List<WebElement> optionTabs;

    @FindBy(xpath = "//div[@class= 'options']//div[contains(@class, 'option selected')]")
    public WebElement selectedTab;

    @FindBy(xpath = "//div[@data-testid='campaign-name-input']//input")
    public WebElement campaignNameInput;

    public CreateCampaignModalComponent(DynamicDriverManager driverManager) {
        super(driverManager);
    }

    public void setCampaignName(String campaignName) {
        typeWithClear(campaignNameInput, campaignName);
    }

    public void selectCampaignType(String type) {
        waitForAllElements(optionTabs);
        getElementByText(optionTabs, type).click();
        waitForElementTextToBe(selectedTab, type);
    }

    public void confirmCampaignCreation() {
        String createCampaignButtonText = "Create Campaign";
        this.clickFooterButtonByText(createCampaignButtonText);
        waitForElementToDissapear(mainDialog);
    }
}
