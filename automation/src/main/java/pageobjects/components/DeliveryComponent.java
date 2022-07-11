package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.util.List;

public class DeliveryComponent extends Component {

    @FindBy(xpath = "//div[@class ='composer-delivery-selector']")
    public WebElement mainComponent;

    @FindBy(xpath = "//div[@class ='composer-delivery-selector']//div[@class ='options']//p")
    public List<WebElement> deliveryTypes;

    @FindBy(xpath = "//div[@class='options']//div[contains(@class,'selected')]//p")
    public WebElement selectedDeliveryType;

    @FindBy(xpath = "//div[@data-test-id='start-type-picker']")
    public WebElement startTypeInput;

    @FindBy(xpath = "//div[@data-testid='start-date-picker']//label[contains(@class, 'date-picker')]//input")
    public WebElement startDateInput;

    @FindBy(xpath = "//div[@data-testid='start-date-picker']//div[@class='time-mode-options']")
    public WebElement startTimeOptions;

    @FindBy(xpath = "//div[@data-testid='start-date-picker']//label[contains(@class, 'time-picker')]//input")
    public WebElement startTimeInput;


    @FindBy(xpath = "//div[@class='option-box end']/div[contains(@class, 'dropdowninput')]")
    public WebElement endTypeInput;


    public DeliveryComponent(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForElement(mainComponent);
    }

    public void chooseDeliveryType(String deliveryType){
        getElementByText(deliveryTypes, deliveryType).click();
        waitForElementTextToBe(selectedDeliveryType, deliveryType);
    }

    public String getDeliveryType(){
        return selectedDeliveryType.getText();
    }

    public void setStartDate(String date){
        waitForElementToBeClickable(startDateInput);
        startDateInput.click();
        CalendarPicker calendarPicker = new CalendarPicker(driverManager);
        calendarPicker.selectDate(date);
    }

    public void setStartOption(String option){
        startTypeInput.click();
        DropdownPopup dropdown = new DropdownPopup(driverManager);
        dropdown.selectOption(option);
    }

    public void setStartTime(String time){
        startTimeInput.click();
        typeWithClear(startTimeInput, time + Keys.ENTER);
        waitForElementValueToBe(startTimeInput, time);
    }

    public void setStartTimeOption(String option){
        startTimeOptions.click();
        DropdownPopup dropdown = new DropdownPopup(driverManager);
        dropdown.selectOption(option);
    }

    public void setEndType(String option){
        endTypeInput.click();
        DropdownPopup dropdown = new DropdownPopup(driverManager);
        dropdown.selectOption(option);
    }
}
