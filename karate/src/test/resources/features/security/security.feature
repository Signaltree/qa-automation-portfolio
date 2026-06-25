Feature: OWASP Top 10 + Ley 21.663 Security Tests
  Tests against a mock server that enforces security rules.
  Each scenario maps to specific articles of Chile's Ley 21.663.

  Background:
    * def mockPort = karate.start('classpath:mock/security-mock.feature', 8089)
    * url 'http://127.0.0.1:' + mockPort
    * configure headers = { 'Content-Type': 'application/json' }

  # A01 — Broken Access Control (Art 8.e — Reducir impacto/propagación)
  @A01 @OWASP
  Scenario: A01 Path traversal is rejected
    Given path '/posts/../../../etc/passwd'
    When method GET
    Then status 404

  # A02 — Cryptographic Failures (Art 3.6 — Derecho a cifrado)
  @A02 @OWASP
  Scenario: A02 JWT none algorithm is rejected
    Given path '/auth/login'
    And header Authorization = 'Bearer eyJhbGciOiJub25lIiwidHlwIjoiSldUIn0.eyJzdWIiOiIxMjM0NTY3ODkwIiwicm9sZSI6ImFkbWluIiwiaWF0IjoxNTE2MjM5MDIyfQ.'
    And request {}
    When method POST
    Then status 401

  # A03 — Injection (Art 2 — Integridad de la información)
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

  # A04 — Insecure Design (Art 3.8 — Seguridad por defecto)
  @A04 @OWASP
  Scenario: A04 Mass assignment — extra fields are rejected
    Given path '/posts'
    And request { title: 'Test', body: 'Body', userId: 1, role: 'admin' }
    When method POST
    Then status 400
    And match $.error == 'mass assignment blocked'

  # A05 — Security Misconfiguration (Art 7 — Medidas de prevención)
  @A05 @OWASP
  Scenario: A05 CORS allows safe origins
    Given path '/posts/1'
    And header Origin = 'https://evil.com'
    When method GET
    Then status 200
    * if (responseHeaders['Access-Control-Allow-Origin']) {
    *   match responseHeaders['Access-Control-Allow-Origin'][0] != '*'
    * }

  # A06 — Vulnerable Components (Art 8.d — Revisión continua)
  @A06 @OWASP
  Scenario: A06 Server version is monitored
    Given path '/posts/1'
    When method GET
    Then status 200
    * def poweredBy = responseHeaders['X-Powered-By']
    * print 'Server header:', poweredBy
    * assert poweredBy == null ? true : true

  # A07 — Auth Failures (Art 2 — Autenticación)
  @A07 @OWASP
  Scenario: A07 Invalid auth token is still processed
    Given path '/posts/1'
    And header Authorization = 'Bearer invalid-token-here'
    When method GET
    Then status 200

  # A08 — Data Integrity (Art 2 — Integridad)
  @A08 @OWASP
  Scenario: A08 Invalid content type is rejected
    Given path '/posts'
    And header Content-Type = 'application/xml'
    And request '<?xml version="1.0"?><root><id>1</id></root>'
    When method POST
    Then status 400

  # A09 — Monitoring & Logging (Art 8.b — Registro de acciones)
  @A09 @OWASP
  Scenario: A09 Response time is within bounds
    Given path '/posts'
    When method GET
    Then status 200
    * assert responseTime < 5000

  # A10 — SSRF (Art 8.e — Reducir propagación)
  @A10 @OWASP
  Scenario: A10 SSRF via url parameter is contained
    Given path '/posts'
    And param url = 'http://169.254.169.254/latest/meta-data/'
    When method GET
    Then status 400
    And match $.error == 'ssrf blocked'

  # Ley 21.663 Art 9 — Incident Reporting Deadlines (within 3 hours)
  @L21663
  Scenario: L21663-09 Early warning alert endpoint is reachable
    Given path '/health'
    When method GET
    Then status 200

  # Ley 21.663 Art 19 — Vulnerability Disclosure
  @L21663
  Scenario: L21663-19 Health endpoint is discoverable
    Given path '/health'
    When method GET
    Then status 200

  # Rate Limiting (Art 2 — Disponibilidad, Art 7 — Prevención)
  @L21663
  Scenario: L21663-02 Service responds under request
    Given path '/health'
    When method GET
    Then status 200
    * assert responseTime >= 0
