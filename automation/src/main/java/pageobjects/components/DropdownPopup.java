package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.util.List;

public class DropdownPopup extends Component {

    @FindBy(xpath="//ul[contains(@class, 'dropdown-popup')]")
    public WebElement dropdown;

    @FindBy(xpath="//ul[contains(@class, 'dropdown-popup')]//li")
    public List<WebElement> options;

    public DropdownPopup(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForElement(dropdown);
    }

    public void selectOption(String option){
        selectOption(option, 0);
    }
    public void selectOption(String option, int waitIntervalMilisec){
        waitForAllElements(options);
        WebElement optionElement = getElementByText(options, option);
        actionsClick(optionElement, waitIntervalMilisec);
        waitForElementToDissapear(dropdown);
    }
}
