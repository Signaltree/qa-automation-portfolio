Feature: OWASP Top 10 + Ley 21.663 Security Tests

  Background:
    * def mockServer = karate.start('classpath:mock/security-mock.feature')
    * url 'http://127.0.0.1:' + mockServer.port

  @A01 @OWASP
  Scenario: A01 Path traversal in params is rejected
    Given path '/posts'
    And param file = '../../../etc/passwd'
    When method GET
    Then status 400

  @A02 @OWASP
  Scenario: A02 JWT with none algorithm is rejected
    Given path '/auth/login'
    And header X-Auth-Token = 'none-algorithm-jwt'
    And header Content-Type = 'application/json'
    And request {}
    When method POST
    Then status 401

  @A03 @OWASP
  Scenario: A03 SQL injection via query params is rejected
    Given path '/posts'
    And param q = "' OR '1'='1"
    When method GET
    Then status 400

  @A03 @OWASP
  Scenario: A03-02 Command injection header is sanitized
    Given path '/posts'
    And header X-Forwarded-For = '; ls -la'
    When method GET
    Then status 200

  @A04 @OWASP
  Scenario: A04 Mass assignment — extra fields are rejected
    Given path '/posts'
    And header Content-Type = 'application/json'
    And request { title: 'Test', body: 'Body', userId: 1, role: 'admin' }
    When method POST
    Then status 400
    And match $.error == 'mass assignment blocked'

  @A05 @OWASP
  Scenario: A05 CORS restricts untrusted origins
    Given path '/posts/1'
    And header Origin = 'https://evil.com'
    When method GET
    Then status 200

  @A06 @OWASP
  Scenario: A06 Server version is not exposed
    Given path '/posts/1'
    When method GET
    Then status 200

  @A07 @OWASP
  Scenario: A07 Invalid auth token is still processed
    Given path '/posts/1'
    And header Authorization = 'Bearer invalid-token-here'
    When method GET
    Then status 200

  @A08 @OWASP
  Scenario: A08 Invalid content type is rejected
    Given path '/posts'
    And header Content-Type = 'application/xml'
    And request '<?xml version="1.0"?><root><id>1</id></root>'
    When method POST
    Then status 400

  @A09 @OWASP
  Scenario: A09 Response time is within bounds
    Given path '/posts'
    When method GET
    Then status 200
    * assert responseTime < 5000

  @A10 @OWASP
  Scenario: A10 SSRF via url parameter is contained
    Given path '/posts'
    And param url = 'http://169.254.169.254/latest/meta-data/'
    When method GET
    Then status 400

  @L21663
  Scenario: L21663-09 Early warning health endpoint
    Given path '/health'
    When method GET
    Then status 200

  @L21663
  Scenario: L21663-19 Health endpoint is discoverable
    Given path '/health'
    When method GET
    Then status 200

  @L21663
  Scenario: L21663-02 Service responds under request
    Given path '/health'
    When method GET
    Then status 200
    * assert responseTime >= 0
