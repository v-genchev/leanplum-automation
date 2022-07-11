package steps;

import drivermanager.DynamicDriverManager;
import io.cucumber.java.en.And;
import org.testng.Assert;
import pageobjects.CampaignsPage;
import pageobjects.EditCampaignPage;
import pageobjects.EditMultiMessageCampaignPage;
import pageobjects.components.CreateCampaignModalComponent;
import pageobjects.components.DeliveryComponent;
import pageobjects.components.ChooseTemplateComponent;
import pageobjects.components.RecurringDeliveryComponent;

import java.util.List;

public class CampaignSteps {

    public DynamicDriverManager driverManger;

    public CampaignSteps(DynamicDriverManager driverManager) {
        this.driverManger = driverManager;
    }

    @And("^I start the creation of a (Single Message|Multi-Message) Campaign(?: with name (.*))?$")
    public void startCampaignCreation(String campaignType, String campaignName) {
        new CampaignsPage(driverManger).startCampaignCreation();
        CreateCampaignModalComponent createCampaignModal = new CreateCampaignModalComponent(driverManger);
        createCampaignModal.selectCampaignType(campaignType);
        createCampaignModal.setCampaignName(campaignName);
        Assert.assertEquals(createCampaignModal.getDialogHeader(), "Create Campaign",
                "Modal dialog header different than expected");
    }

    @And("^I confirm Campaign creation$")
    public void confirmCampaignCreation() {
        new CreateCampaignModalComponent(driverManger).confirmCampaignCreation();
    }

    @And("^I start editing the (Target Audience|Delivery Method|Actions) section$")
    public void chooseEditSection(String section) {
        EditMultiMessageCampaignPage editCampaignPage = new EditMultiMessageCampaignPage(driverManger);
        editCampaignPage.selectTab(section);
    }

    @And("^I verify (Target Audience|Delivery Method|Actions) section summary:")
    public void verifySectionSummary(String section, List<String> expectedSummary) {
        EditMultiMessageCampaignPage editCampaignPage = new EditMultiMessageCampaignPage(driverManger);
        List<String> actualSummary = editCampaignPage.getTabContents(section);
        Assert.assertEquals(actualSummary, expectedSummary, String.format("%s section Summary not as expected", section));
    }

    @And("^I choose (.*) delivery type")
    public void chooseDeliveryType(String deliveryType) {
        DeliveryComponent deliveryMethod = new DeliveryComponent(driverManger);
        deliveryMethod.chooseDeliveryType(deliveryType);
    }

    @And("^I want the (?:.*)delivery to (?:(Start(?: on| immediately after publish)) (.*?))?(?: in (.*?))?(?: at (.*? (?:AM|PM)))?$")
    public void setStart(String when, String date, String timeOption, String time) {
        DeliveryComponent deliveryComponent = new DeliveryComponent(driverManger);
        if(when != null && !deliveryComponent.getDeliveryType().equals("Recurring")){
            deliveryComponent.setStartOption(when);
        }
        if(date != null) { deliveryComponent.setStartDate(date); }
        if(timeOption != null) { deliveryComponent.setStartTimeOption(timeOption); }
        if(time != null) { deliveryComponent.setStartTime(time); }
    }

    @And("^I want the Recurring delivery to (Finish after) (.*?) occurrences")
    public void setRecurringEnd(String endType, String occurrences) {
        RecurringDeliveryComponent recurringDeliveryComponent = new RecurringDeliveryComponent(driverManger);
        recurringDeliveryComponent.setEndType(endType);
        recurringDeliveryComponent.setEndOccurrences(occurrences);
    }

    @And("^I want the Recurring delivery to be repeated every (.*?) (.*)$")
    public void repeatEvery(String number, String period) {
        RecurringDeliveryComponent deliveryComponent = new RecurringDeliveryComponent(driverManger);
        deliveryComponent.setRepeatValues(number, period);
    }

    @And("^I add (.*) action from the (.*) category$")
    public void addAction(String templateToApply, String category) {
        EditMultiMessageCampaignPage editCampaign = new EditMultiMessageCampaignPage(driverManger);
        editCampaign.addCampaignActionButton.click();
        ChooseTemplateComponent templateComponent = new ChooseTemplateComponent(driverManger);
        templateComponent.selectTemplate(category, templateToApply);
        templateComponent.submitModal();
    }

    @And("^I select (.*) step to edit$")
    public void navigateToStep(String stepSubTab) {
        EditCampaignPage editCampaignPage = new EditCampaignPage(driverManger);
        editCampaignPage.navigateToStep(stepSubTab);
    }

}
