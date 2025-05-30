Feature: Salesforce Blog Search

  Scenario: User searches for a keyword and validates number of results
    Given I launch the Salesforce blog page
#    When I search for the keyword "agent"
#    Then I should see search results displayed
    And I should see Share Article Section
    Then I verify the social media links available