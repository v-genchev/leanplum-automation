package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.util.List;
import java.util.Optional;

public class DashboardNavigationComponent extends Component {

    @FindBy(xpath = "//div[@class='nav main']/*[self::div[contains(@class, 'link-group')] or self::a[@class='link']]")
    public List<WebElement> mainMenus;

    @FindBy(xpath = "//div[@class='nav main']//a[@class='sub-link']")
    public List<WebElement> subMenus;

    @FindBy(xpath = "//div[contains(@class, 'secondary-nav')]//a")
    public List<WebElement> secondaryTabs;

    @FindBy(xpath = "//div[contains(@class, 'secondary-nav')]//a[contains(@class, 'active')]")
    public WebElement activeTab;

    @FindBy(xpath = "//div[@class='nav main']//a[contains(@class, 'link-active')]")
    public WebElement activeMenu;

    public DashboardNavigationComponent(DynamicDriverManager driverManager) {
        super(driverManager);
    }

    public void selectMenu(String mainMenu, String subMenu) {
        String activeMenuName = getElementTextContent(activeMenu);
        String mainMenuSearch = StringUtils.capitalize(mainMenu.toLowerCase());
        if (!(activeMenuName.equals(mainMenuSearch) || activeMenuName.equals(subMenu))) {
            getElementByText(mainMenus, mainMenu.toUpperCase()).click();
            if (subMenu != null) {
                getElementByText(subMenus, subMenu).click();
            }
        } else {
            logger.info(String.format("Menu %s/%s already active", mainMenu, Optional.ofNullable(subMenu).orElse("")));
        }
    }

    public void switchTab(String tabName){
        getElementByText(secondaryTabs, tabName).click();
        waitForElementTextToBe(activeTab, tabName);
    }
}
