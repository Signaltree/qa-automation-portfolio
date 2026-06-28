Feature: GraphQL API — Countries

  Background:
    * url 'https://countries.trevorblades.com/graphql'
    * configure headers = { 'Content-Type': 'application/json' }

  Scenario: Query all countries returns names and codes
    Given request { query: 'query { countries { name code } }' }
    When method POST
    Then status 200
    And match $.data.countries == '#array'
    And match each $.data.countries contains { name: '#string', code: '#string' }

  Scenario: Query single country by code
    Given request { query: 'query { country(code: \"CL\") { name capital currency } }' }
    When method POST
    Then status 200
    And match $.data.country.name == 'Chile'
    And match $.data.country.capital == 'Santiago'
    And match $.data.country.currency == '#string'

  Scenario: Query nested continent data
    Given request { query: 'query { continent(code: \"SA\") { name countries { name } } }' }
    When method POST
    Then status 200
    And match $.data.continent.name == 'South America'
    And match each $.data.continent.countries contains { name: '#string' }

  Scenario: Query language and its countries
    Given request { query: '{ language(code: \"es\") { name native countries { name } } }' }
    When method POST
    Then status 200
    And match $.data.language.name == 'Spanish'
    And match $.data.language.countries == '#array'

  Scenario: Multiple queries in one request
    Given request { query: 'query { country(code: \"BR\") { name capital } continent(code: \"EU\") { name } }' }
    When method POST
    Then status 200
    And match $.data.country.name == 'Brazil'
    And match $.data.continent.name == 'Europe'

  Scenario: Invalid query returns errors
    Given request { query: 'query { invalidField }' }
    When method POST
    Then status 200
    And match $.errors == '#array'
    And match $.errors[0].message == '#string'

  Scenario: Response time is acceptable
    Given request { query: 'query { countries { name } }' }
    When method POST
    Then status 200
    And assert responseTime < 5000
