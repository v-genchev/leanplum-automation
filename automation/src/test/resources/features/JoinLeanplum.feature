@now
Feature: Create Campaign

  Scenario: Schedule and start a campaign with a push message.

    * I start the browser

    Given I navigate to home page
    And I select menu Login
    And I login with email shouldBeReadFromPropertiesAndEncrypted and password shouldBeReadFromPropertiesAndEncrypted
    And I close the initial overlay message
    And I verify page title is Campaigns — Leanplum

    When I navigate to CAMPAIGNS/Campaigns menu
    And I start the creation of a Multi-Message Campaign with name Test Campaign
    And I confirm Campaign creation
    And I verify Target Audience section summary:
      | TARGET AUDIENCE |
      | All Users       |
    And I start editing the Delivery Method section
    And I choose Recurring delivery type
    And I want the Recurring delivery to Start on 07/15/2022 in User's Timezone at 03:00 PM
    And I want the Recurring delivery to be repeated every 2 Days
    And I want the Recurring delivery to Finish after 5 occurrences
    And I verify Delivery Method section summary:
      | DELIVERY METHOD: RECURRING                    |
      | Start on 15 Jul 2022, 3:00 PM User's Timezone |
      | Repeat every 2 days                           |
      | End after 5 occurrences                       |
    And I start editing the Actions section
    And I add Push Notification action from the Push Notification category
    And I select Action step to edit


#    * I stop the browser