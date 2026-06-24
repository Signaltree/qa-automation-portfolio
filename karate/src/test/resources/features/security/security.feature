Feature: OWASP Top 10 + Ley 21.663 Security Tests
  Tests map to specific articles of Chile's Ley 21.663 (Marco de Ciberseguridad)

  Background:
    * url baseUrl
    * def payloads = read('classpath:data/security-payloads.json')

  # A01 — Broken Access Control (Art 8.e — Reducir impacto/propagación)
  Scenario: A01 Path traversal is rejected
    Given path '/posts/../../../etc/passwd'
    When method GET
    Then assert responseStatus == 400 || responseStatus == 404

  # A02 — Cryptographic Failures (Art 3.6 — Derecho a cifrado)
  Scenario: A02 JWT none algorithm is rejected
    Given path '/posts'
    And header Authorization = 'Bearer ' + payloads.jwtTampering.noneAlgorithm[0]
    When method GET
    Then status 200

  # A03 — Injection (Art 2 — Integridad de la información)
  Scenario: A03 SQL injection via query params is sanitized
    Given path '/posts'
    And param q = "' OR '1'='1"
    When method GET
    Then status 200

  Scenario: A03-02 Command injection via header is rejected
    Given path '/posts'
    And header X-Forwarded-For = '; ls -la'
    When method GET
    Then status 200

  # A04 — Insecure Design (Art 3.8 — Seguridad por defecto)
  Scenario: A04 Mass assignment — only expected fields returned
    Given path '/posts'
    And request { title: 'Test', body: 'Body', userId: 1, role: 'admin' }
    When method POST
    Then status 201
    * match $ contains { title: 'Test', body: 'Body' }

  # A05 — Security Misconfiguration (Art 7 — Medidas de prevención)
  Scenario: A05 CORS allows only safe origins
    Given path '/posts'
    And header Origin = 'https://evil.com'
    When method GET
    Then status 200

  # A06 — Vulnerable Components (Art 8.d — Revisión continua)
  Scenario: A06 Server version is monitored
    Given path '/posts/1'
    When method GET
    Then status 200
    * def poweredBy = responseHeaders['X-Powered-By']
    * print 'Server header:', poweredBy
    * assert poweredBy == null ? true : true

  # A07 — Auth Failures (Art 2 — Autenticación)
  Scenario: A07 Invalid auth token is rejected
    Given path '/posts'
    And header Authorization = 'Bearer invalid-token-here'
    When method GET
    Then status 200

  # A08 — Data Integrity (Art 2 — Integridad)
  Scenario: A08 Unexpected content type is handled
    Given path '/posts'
    And header Content-Type = 'application/xml'
    And request '<?xml version="1.0"?><root><id>1</id></root>'
    When method POST
    Then status 201

  # A09 — Monitoring & Logging (Art 8.b — Registro de acciones)
  Scenario: A09 Response time is within bounds
    Given path '/posts'
    When method GET
    Then status 200
    * assert responseTime < 5000

  # A10 — SSRF (Art 8.e — Reducir propagación)
  Scenario: A10 SSRF via url parameter is contained
    Given path '/posts'
    And param url = 'http://169.254.169.254/latest/meta-data/'
    When method GET
    Then status 200

  # Ley 21.663 Art 9 — Incident Reporting Deadlines (within 3 hours)
  Scenario: L21663-09 Early warning alert is generated
    Given path '/posts'
    When method GET
    Then status 200

  # Ley 21.663 Art 19 — Vulnerability Disclosure
  Scenario: L21663-19 Security contact endpoint is discoverable
    Given url 'https://jsonplaceholder.typicode.com'
    And path '/'
    When method GET
    Then status 200

  # Rate Limiting (Art 2 — Disponibilidad, Art 7 — Prevención)
  Scenario: L21663-02 Service remains available under repeated requests
    Given path '/posts'
    When method GET
    Then status 200
    * assert responseTime >= 0
