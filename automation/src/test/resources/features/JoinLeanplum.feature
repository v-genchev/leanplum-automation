@now
Feature: Create Campaign

  Background:
    Given I start the browser
    And I navigate to home page
    And I select menu Login
    And I login with email _Config_[user.email] and password _Config_[user.password]
    And I close the initial overlay message

  Scenario: Schedule and start a campaign with a push message.

    When I navigate to CAMPAIGNS/Campaigns menu
    And I start the creation of a Multi-Message Campaign with name Test Campaign
    And I confirm Campaign creation
    And I verify Target Audience section summary:
      | TARGET AUDIENCE |
      | All Users       |
    And I start editing the Delivery Method section
    And I choose Recurring delivery type
    And I want the Recurring delivery to Start on _PrettyDate_[2 days from now] in User's Timezone at 03:00 PM
    And I want the Recurring delivery to be repeated every 1 Days
    And I want the Recurring delivery to Finish after 5 occurrences
    And I verify Delivery Method section summary:
      | DELIVERY METHOD: RECURRING                                      |
      | Start on _PrettyDate_[2 days from now], 3:00 PM User's Timezone |
      | Repeat every day                                                |
      | End after 5 occurrences                                         |
    And I start editing the Actions section
    And I add Push Notification action from the Push Notification category
    And I select Action step to edit
    And I set action main Message text to Looking forward to the next interview
    And I Preview & Test the Action
    Then I verify push notification content preview message is Looking forward to the next interview
    And I verify Actions section summary:
      | ACTIONS: PUSH NOTIFICATION |
      | 1 action                   |
    And I check campaign current status is DRAFT
    When I review the campaign
    And I verify campaign summary items:
      | Target Audience: 100% of all Users |
      | Delivery Method: Recurring         |
      | Actions: Push Notification         |
    And I publish the campaign
    And I check campaign current status is PUBLISHED
    And I finish the campaign
    Then I verify campaign current status is FINISHED

    * I stop the browser