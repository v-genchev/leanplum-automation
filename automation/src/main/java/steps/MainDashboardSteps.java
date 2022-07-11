package steps;

import drivermanager.DynamicDriverManager;
import io.cucumber.java.en.And;
import pageobjects.components.DashboardNavigationComponent;
import pageobjects.components.MessageOverlayComponent;

public class MainDashboardSteps {

    public DynamicDriverManager driverManger;

    public MainDashboardSteps(DynamicDriverManager driverManager) {
        this.driverManger = driverManager;
    }

    @And("^I close the initial overlay message$")
    public void closeOverlay() {
        new MessageOverlayComponent(driverManger).closeMessage();
    }

    @And("^I navigate to (.*?)(?:/(.*))? menu$")
    public void selectMenu(String mainMenu, String subMenu) {
        DashboardNavigationComponent dashboardNavigation = new DashboardNavigationComponent(driverManger);
        dashboardNavigation.selectMenu(mainMenu, subMenu);
    }

}
