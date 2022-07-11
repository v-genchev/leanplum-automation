package pageobjects.components;

import drivermanager.DynamicDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import java.time.temporal.ChronoField;
import java.util.List;

public class CalendarPicker extends Component {

    @FindBy(xpath = "//div[@class = 'lp-calendar shadow']")
    public WebElement calendar;

    @FindBy(xpath = "//div[contains(@class, 'calendar-controls')]//div[@data-testid='next']")
    public WebElement nextMonthButton;

    @FindBy(xpath = "//div[contains(@class, 'calendar-controls')]//div[@data-testid='previous']")
    public WebElement previousMonthButton;

    @FindBy(xpath = "//div[contains(@class, 'calendar-month')]//div[contains(@class, 'lp-day') and not(contains(@class, 'outside-month'))]")
    public List<WebElement> currentMonthDays;

    @FindBy(xpath = "//div[contains(@class, 'calendar-controls')]//div[@class='current']")
    public WebElement currentMonthYearElement;

    @FindBy(xpath = "//div[contains(@class, 'calendar-month')]//div[contains(@class, 'day selected')]")
    public WebElement selectedDay;


    public CalendarPicker(DynamicDriverManager driverManager) {
        super(driverManager);
        waitForPage(calendar);
    }

    public void selectDate(String dateToSet) {
        selectDate(dateToSet, "MM/dd/yyyy");
    }

    public void selectDate(String dateToSet, String dateFormat) {
        String currentDateText = currentMonthYearElement.getText();
        int currentDay = Integer.parseInt(selectedDay.getText());
        DateTimeFormatter currentDateFormat = new DateTimeFormatterBuilder().
                                                                appendPattern("MMMM yyyy").
                                                                parseDefaulting(ChronoField.DAY_OF_MONTH, currentDay).
                                                                toFormatter();
        DateTimeFormatter dateToSetFormat = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate currentDate = LocalDate.parse(currentDateText, currentDateFormat);
        LocalDate desiredDate = LocalDate.parse(dateToSet, dateToSetFormat);

        int monthsBetween = Period.between(currentDate, desiredDate).getMonths();
        WebElement buttonToClick = monthsBetween < 0 ? previousMonthButton : nextMonthButton;
        for (int i = 0; i < Math.abs(monthsBetween); i++) {
            buttonToClick.click();
        }

        selectDay(String.valueOf(desiredDate.getDayOfMonth()));
    }

    public void selectDay(String day) {
        getElementByText(currentMonthDays, day).click();
        waitForElementToDissapear(calendar);
    }
}
