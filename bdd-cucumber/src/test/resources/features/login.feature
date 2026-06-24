Feature: SauceDemo Login
  As a standard user
  I want to log into SauceDemo
  So that I can access the inventory

  Background:
    Given I am on the SauceDemo login page

  @smoke @happy-path
  Scenario: Successful login with standard user
    When I log in with username "standard_user" and password "secret_sauce"
    Then I should see the inventory page
    And the page title should be "Swag Labs"
    And the inventory should contain 6 items

  @smoke @locked-out
  Scenario: Locked out user cannot log in
    When I log in with username "locked_out_user" and password "secret_sauce"
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."
    And I should remain on the login page

  @smoke @error-state
  Scenario Outline: Invalid credentials show error message
    When I log in with username "<username>" and password "<password>"
    Then I should see an error message
    And I should remain on the login page

    Examples:
      | username        | password        |
      | invalid_user    | wrong_password  |
      | standard_user   | wrong_password  |
      | ""              | ""              |

  @inventory @sorting
  Scenario: Products can be sorted by price (low to high)
    Given I am logged in as "standard_user"
    When I sort products by "Price (low to high)"
    Then the products should be displayed in ascending price order

  @visual @problem-user
  Scenario: Problem user displays broken images
    Given I am logged in as "problem_user"
    When I view the inventory page
    Then some product images should be broken or incorrect

  @checkout @complete-flow
  Scenario: Complete purchase flow
    Given I am logged in as "standard_user"
    When I add "Sauce Labs Backpack" to the cart
    And I proceed to checkout
    And I enter shipping information "John", "Doe", "12345"
    And I complete the purchase
    Then I should see the order confirmation
