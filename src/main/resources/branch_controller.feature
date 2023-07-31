#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Getting bank branches by IFSC code and city

  Scenario: Get bank branch by IFSC code
    Given a valid IFSC code "ABC123"
    When I request the branch details for the IFSC code
    Then the response status should be 200 OK
    And the response should contain the following data:
      | ifscCode | branch     | address       | city       | district    | state      | bank       |
      | ABC123   | Test Branch| Test Address  | Test City  | Test District| Test State| Test Bank  |

  Scenario: Get bank branches by city
    Given a valid city "Test City"
    When I request the branches in the city
    Then the response status should be 200 OK
    And the response should contain the following data:
      | ifscCode | branch     | address       | city       | district    | state      | bank       |
      | ABC123   | Test Branch| Test Address  | Test City  | Test District| Test State| Test Bank  |

  Scenario: Get bank branch by invalid IFSC code
    Given an invalid IFSC code "InvalidCode"
    When I request the branch details for the IFSC code
    Then the response status should be 404 Not Found
    And the response should contain the error message "Branch not found for IFSC code: InvalidCode"

  Scenario: Get bank branches for non-existent city
    Given a non-existent city "NonExistentCity"
    When I request the branches in the city
    Then the response status should be 404 Not Found
    And the response should contain the error message "No Bank Branches are present in the city: NonExistentCity"
