Feature: JSONPlaceholder Users API

  Background:
    * url baseUrl
    * configure headers = { 'Content-Type': 'application/json' }

  Scenario: GET all users returns valid structure
    Given path '/users'
    When method GET
    Then status 200
    * assert karate.sizeOf(response) > 0
    * match each $ contains { id: '#number', name: '#string', username: '#string', email: '#string' }
    * def firstUser = $[0]
    * match firstUser.address contains { street: '#string', city: '#string' }

  Scenario: GET user by ID
    Given path '/users', 1
    When method GET
    Then status 200
    * match $.id == 1
    * match $.name == 'Leanne Graham'
    * match $.email == '#string'

  Scenario: GET user posts via relationship
    Given path '/users', 1, 'posts'
    When method GET
    Then status 200
    * match each $ contains { userId: 1 }

  Scenario: GET user with query parameters
    Given path '/users'
    And param username = 'Bret'
    When method GET
    Then status 200
    * match $[0].username == 'Bret'

  Scenario: Schema validation for address fields
    Given path '/users', 1
    When method GET
    Then status 200
    * def addr = $.address
    * match addr == { street: '#string', suite: '#string', city: '#string', zipcode: '#string', geo: { lat: '#string', lng: '#string' } }

  Scenario: Response time is acceptable
    Given path '/users'
    When method GET
    Then status 200
    * assert responseTime < 3000

  Scenario: Verify response headers
    Given path '/users', 1
    When method GET
    Then status 200
    * match responseHeaders['Content-Type'][0] contains 'application/json'
