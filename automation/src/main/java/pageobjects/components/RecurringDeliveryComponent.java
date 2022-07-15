package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecurringDeliveryComponent extends DeliveryComponent {

    @FindBy(xpath="//div[@data-testid='recurrence-step-picker']//input[contains(@class, 'number')]")
    public WebElement repeatEveryNumberInput;

    @FindBy(xpath="//div[@data-testid='recurrence-step-picker']//div[contains(@class, 'dropdowninput')]")
    public WebElement repeatEveryPeriodInput;

    @FindBy(xpath="//div[@data-testid='recurrence-occurrences-picker']//input")
    public WebElement endOccurrencesInput;
    public RecurringDeliveryComponent(DynamicDriverManager driverManager) {
        super(driverManager);
    }

    public void setRepeatValues(String number, String period){
        waitForElementToBeClickable(repeatEveryPeriodInput);
        repeatEveryPeriodInput.click();
        DropdownPopup dropdown = new DropdownPopup(driverManager);
        dropdown.selectOption(period);
        repeatEveryNumberInput.click();
        typeWithClear(repeatEveryNumberInput, number + Keys.ENTER);
        waitForElementValueToBe(repeatEveryNumberInput, number);
    }

    public void setEndOccurrences(String occurrences){
        endOccurrencesInput.click();
        typeWithClear(endOccurrencesInput, occurrences + Keys.ENTER);
        waitForElementValueToBe(endOccurrencesInput, occurrences);
    }
}
