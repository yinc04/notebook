Feature: Manage Categories in Notebook

  Scenario: Add a new category
    Given I am on the notebook page
    When I click on "Add Category" button
    And I enter "Work" as the category name
    And I click on "Confirm"
    Then the new category "Work" should be added to the list
