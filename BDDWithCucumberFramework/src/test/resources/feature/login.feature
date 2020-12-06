Feature: actiTIME Login

  Scenario: Validate if the login feature of the actiTIME application is working fine

    Given The browser is open
    And The browser window is maximized
    When The user is in actiTIME login page and the page title is "actiTIME - Login"
    Then The user enters username and password into the respective fields
    And The user clicks on the login button
    And The user should redirect to the home page and page title should be "actiTIME - Enter Time-Track"
    And The user clicks on the logout button
    And Closes the browser