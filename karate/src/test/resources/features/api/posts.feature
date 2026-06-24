Feature: JSONPlaceholder Posts API

  Background:
    * url baseUrl
    * configure headers = { 'Content-Type': 'application/json' }

  Scenario: GET all posts returns list
    Given path '/posts'
    When method GET
    Then status 200
    * assert karate.sizeOf(response) > 0
    * match each $ == { userId: '#number', id: '#number', title: '#string', body: '#string' }

  Scenario: GET single post returns correct structure
    Given path '/posts', 1
    When method GET
    Then status 200
    * match $ == { userId: '#number', id: '#number', title: '#string', body: '#string' }
    * match $.id == 1

  Scenario: GET non-existent post returns 404
    Given path '/posts', 999999
    When method GET
    Then status 404

  Scenario: POST creates a new post
    Given path '/posts'
    And request read('classpath:data/test-data.json').post.newPost
    When method POST
    Then status 201
    * match $ contains { title: '#string', body: '#string', userId: '#number', id: '#number' }
    * match $.title == read('classpath:data/test-data.json').post.newPost.title

  Scenario: PUT updates existing post
    Given path '/posts', 1
    And request read('classpath:data/test-data.json').post.updatePost
    When method PUT
    Then status 200
    * match $.title == read('classpath:data/test-data.json').post.updatePost.title

  Scenario: PATCH partially updates post
    Given path '/posts', 1
    And request { title: 'Partially Updated' }
    When method PATCH
    Then status 200
    * match $.title == 'Partially Updated'

  Scenario: DELETE removes a post
    Given path '/posts', 1
    When method DELETE
    Then status 200

  Scenario: GET posts filtered by userId
    Given path '/posts'
    And param userId = 1
    When method GET
    Then status 200
    * match each $ contains { userId: 1 }

  Scenario: POST with empty body returns validation error
    Given path '/posts'
    And request read('classpath:data/test-data.json').post.invalidPost
    When method POST
    Then status 201
    * match $.title == ''
