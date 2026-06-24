Feature: Karate Gatling Load Test Simulation

  Background:
    * url baseUrl

  Scenario: Get all posts
    Given path '/posts'
    When method GET
    Then status 200

  Scenario: Get single post
    Given path '/posts', 1
    When method GET
    Then status 200
    And match $.id == 1

  Scenario: Create a new post
    Given path '/posts'
    And request { title: 'Load Test Post', body: 'Created during load test', userId: 1 }
    When method POST
    Then status 201

  Scenario: Get all users
    Given path '/users'
    When method GET
    Then status 200
    And assert karate.sizeOf(response) == 10

  Scenario: Get user todos
    Given path '/users', 1, 'todos'
    When method GET
    Then status 200

  Scenario: Get post comments
    Given path '/posts', 1, 'comments'
    When method GET
    Then status 200
