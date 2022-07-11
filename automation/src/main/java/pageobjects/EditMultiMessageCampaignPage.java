package pageobjects;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class EditMultiMessageCampaignPage extends EditCampaignPage {

    @FindBy(xpath = "//button[descendant::span[text()='Add Action']]")
    public WebElement addCampaignActionButton;

    @FindBy(xpath = "//a[contains(@class, nav-item)]//p[@class='title']//span")
    public List<WebElement> editSectionTabTitles;

    @FindBy(xpath = "//a[contains(@class, nav-item)]//div[@class='tab']")
    public List<WebElement> editSectionTabs;

    private final By tabDescriptionsLocator = By.xpath(".//div[@class='desc']");
    private final By tabTitleLocator = By.xpath(".//p[@class='title']//span");

    public EditMultiMessageCampaignPage(DynamicDriverManager driverManager) {
        super(driverManager);
    }


    private WebElement getTab(String tabName) {
        WebElement tabTitle = getElementByPartialText(editSectionTabTitles, tabName.toUpperCase());
        return editSectionTabs.get(editSectionTabTitles.indexOf(tabTitle));
    }

    public void selectTab(String tabName){
        getTab(tabName).click();
    }

    protected String getTabTitle(String tabName){
        return getTab(tabName).findElement(this.tabTitleLocator).getText();
    }

    protected List<String> getTabDescription(String tabName){
        List<WebElement> tabDescriptionElements = getTab(tabName).findElements(this.tabDescriptionsLocator);
        return getElementsText(tabDescriptionElements);
    }

    public List<String> getTabContents(String tabName){
        List<String> tabContents = new ArrayList<>();
        tabContents.add(getTabTitle(tabName));
        tabContents.addAll(getTabDescription(tabName));
        return tabContents;
    }
}
