package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.util.List;

public class DropdownPopup extends Component {

    @FindBy(xpath="//ul[contains(@class, 'dropdown-popup')]")
    public WebElement dropdown;

    @FindBy(xpath="//ul[contains(@class, 'dropdown-popup')]//li//div")
    public List<WebElement> options;

    public DropdownPopup(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForElement(dropdown);
    }

    public void selectOption(String option){
        getElementByText(options, option).click();
    }
}
