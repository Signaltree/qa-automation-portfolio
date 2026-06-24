Feature: Inventory Management
  As a logged-in user
  I want to manage my shopping cart
  So that I can purchase products

  Background:
    Given I am logged in as "standard_user"

  @cart @add-item
  Scenario: Add single item to cart
    When I add "Sauce Labs Bike Light" to the cart
    Then the cart badge should show 1

  @cart @remove-item
  Scenario: Remove item from cart
    Given I have "Sauce Labs Backpack" in my cart
    When I remove "Sauce Labs Backpack" from the cart
    Then the cart badge should not be visible

  @cart @multiple-items
  Scenario: Add multiple items to cart
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bolt T-Shirt" to the cart
    And I add "Sauce Labs Onesie" to the cart
    Then the cart badge should show 3

  @cart @persistence
  Scenario: Cart persists across page navigation
    When I add "Sauce Labs Backpack" to the cart
    And I navigate to the cart page
    And I return to the inventory page
    Then the cart badge should show 1
