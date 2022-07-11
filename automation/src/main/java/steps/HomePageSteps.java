package steps;

import drivermanager.DynamicDriverManager;
import io.cucumber.java.en.And;
import pageobjects.HomePage;
import pageobjects.components.MainWebsiteNavigationComponent;

public class HomePageSteps {
    public DynamicDriverManager driverManger;

    public HomePageSteps(DynamicDriverManager driverManager) {
        this.driverManger = driverManager;
    }

    @And("^I navigate to home page$")
    public void navigateToHomePage() {
        new HomePage(driverManger, true);
    }

    @And("^I select menu (.*?)(?:/(.*))?$")
    public void selectMenu(String mainMenu, String subMenu) {
        MainWebsiteNavigationComponent headerSection = new MainWebsiteNavigationComponent(driverManger);
        headerSection.selectMenu(mainMenu, subMenu);
    }
}
