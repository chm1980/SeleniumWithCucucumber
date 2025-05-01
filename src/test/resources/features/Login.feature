Feature: LoginFeature
  This feature verifies the login functionality of the application

  Scenario: Login with correct username and password
    Given I navigate to the login page
    And I enter the following for Login
      | username | password      |
      | admin    | adminpassword |
    And I click login button
    Then I should see the userform page

  Scenario: Login with incorrect credentials
    Given I navigate to the login page
    And I enter the following for Login
      | username | password      |
      | admin    | wrongpassword |
    And I click login button
    Then I should see an error message
