package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.util.List;

public class ChooseTemplateComponent extends Component {

    @FindBy(xpath = "//div[contains(@class,'create-modal')]")
    public WebElement mainDialog;

    @FindBy(xpath = "//div[contains(@class,'create-modal')]//div[@class='header']")
    public WebElement dialogHeader;

    @FindBy(xpath = "//div[contains(@class,'create-modal')]//button")
    public List<WebElement> modalButtons;

    @FindBy(xpath = "//div[contains(@class,'create-modal')]//button[contains(@class, 'primary')]")
    public WebElement modalSubmitButton;

    @FindBy(xpath = "//div[contains(@class,'create-modal')]//ul[contains(@class, 'strip-list')]//span")
    public List<WebElement> categoryList;

    @FindBy(xpath = "//div[contains(@class, 'item-view')]//a")
    public List<WebElement> categoryTemplates;

    public ChooseTemplateComponent(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(mainDialog);
    }

    public String getDialogHeader(){
        return dialogHeader.getText();
    }

    public void submitModal(){
        modalSubmitButton.click();
    }
    protected void clickFooterButtonByText(String buttonText){
        WebElement buttonToClick = getElementByText(modalButtons, buttonText);
        waitForElementToBeClickable(buttonToClick);
        buttonToClick.click();
    }

    public void selectTemplate(String category, String template){
        selectTemplateCategory(category);
        selectVisibleTemplate(template);
    }

    public void selectTemplateCategory(String category){
        getElementByText(categoryList, category).click();
    }

    public void selectVisibleTemplate(String template){
        WebElement templateItem = getElementByText(categoryTemplates, template);
        scrollToView(templateItem);
        templateItem.click();
    }
}
