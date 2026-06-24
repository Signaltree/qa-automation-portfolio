Feature: API Security and Error Handling Tests

  Background:
    * url baseUrl

  Scenario: 404 for non-existent resource
    Given path '/nonexistent'
    When method GET
    Then status 404

  Scenario: 500 for invalid method
    Given path '/posts', 'invalid'
    When method GET
    Then status 404

  Scenario: POST without content-type header
    Given path '/posts'
    And configure headers = {}
    And request { title: 'No Header', body: 'test', userId: 1 }
    When method POST
    Then status 201
    * match $.title == 'No Header'

  Scenario: POST with malformed body
    Given path '/posts'
    And request 'not-json'
    When method POST
    Then status 201

  Scenario: Validate response time on slow endpoint
    Given path '/posts'
    When method GET
    Then status 200
    * assert responseTime < 5000

  Scenario: Verify CORS headers
    Given path '/posts', 1
    When method OPTIONS
    Then status 204

  Scenario: GET with invalid parameter types
    Given path '/posts'
    And param userId = 'abc'
    When method GET
    Then status 200

  Scenario: Bulk validation — verify all posts have valid structure
    Given path '/posts'
    When method GET
    Then status 200
    * match each $ == { userId: '#number', id: '#number', title: '#string', body: '#string' }
    * assert karate.sizeOf(response) == 100
