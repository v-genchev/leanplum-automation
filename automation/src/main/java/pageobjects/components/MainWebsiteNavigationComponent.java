package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.util.List;

public class MainWebsiteNavigationComponent extends Component
{
    @FindBy(id="header")
    public WebElement component;

    @FindBy(xpath="//section[@id='header']//li[contains(@class, 'menu-item') and not(contains(@class, 'lg-none'))]")
    public List<WebElement> mainMenuItems;

    @FindBy(xpath="//div[@class='dropdown-column']//a")
    public List<WebElement> subMenuItems;

    public MainWebsiteNavigationComponent(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(component);
    }

    public void selectMenu(String mainMenu){
        selectMenu(mainMenu, null);
    }

    public void selectMenu(String mainMenu, String subMenu){
        getElementByText(mainMenuItems, mainMenu).click();
        if(subMenu != null){
            getElementByText(subMenuItems, subMenu).click();
        }
    }

}
