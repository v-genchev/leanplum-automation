package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.util.List;

public class ActionComponent extends Component {

     @FindBy(id="action")
     public WebElement action;

     @FindBy(xpath="//div[text()='Preview & Test']")
     public WebElement previewAndTestButton;

     @FindBy(xpath="//div[contains(@class, 'field-editor-view single')]")
     public List<WebElement> visibleFields;

    @FindBy(xpath="//div[contains(@class, 'field-editor-view single')]//div[contains(@class, 'label-view')]//div[@class='field-name']//input")
    public List<WebElement> visibleFieldLabels;

    @FindBy(xpath="//div[contains(@class, 'single')]//div[contains(@class, 'field-value')]//*[self::input or self::div[@data-testid='select-menu']]")
    public List<WebElement> visibleFieldInputs;

    @FindAll({@FindBy(xpath="//span[@class='action-group']//button"),
            @FindBy(xpath = "//span[@class='views']//div[contains(@class, 'group-button')]")})
    public List<WebElement> actionButtons;

     public ActionComponent(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForElement(action);
    }

    public WebElement getInputByLabel(String label){
        List<String> labelsText = getElementsValues(visibleFieldLabels);
        return visibleFieldInputs.get(labelsText.indexOf(label));
    }

    public void populateTextInput(String inputLabel, String text){
         getInputByLabel(inputLabel).sendKeys(text);
    }

    public void clickButtonByText(String buttonText){
         getElementByText(actionButtons, buttonText).click();
    }



}
