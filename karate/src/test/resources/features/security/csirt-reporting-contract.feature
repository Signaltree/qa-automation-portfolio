Feature: CSIRT Nacional Incident Reporting — Ley 21.663 Art 9 Contract Tests
  Validates the structure and deadlines of security incident reports
  as required by Chile's Ley Marco de Ciberseguridad.

  Background:
    * url baseUrl
    * configure headers = { 'Content-Type': 'application/json' }

  # Art 9.a — Alerta Temprana (within 3 hours)
  Scenario: Early warning report follows schema
    Given path '/posts'
    And request { incident_id: 'INC-2026-001', timestamp: '#string', severity: 'high', type: 'suspicious_activity', detected_at: '#string', source: 'monitoring-system-01', status: 'alert' }
    When method POST
    Then status 201

  # Art 9.b — Incident Update (within 72h, 24h for OIV)
  Scenario: Incident update includes initial assessment
    Given path '/posts'
    And request { incident_id: 'INC-2026-001', update_number: 1, assessment: 'Credential stuffing attack — 12 accounts compromised', severity: 'high', impact: '12 user accounts', indicators: 'IP range 185.220.101.x — geolocation RU', containment_status: 'in_progress' }
    When method POST
    Then status 201

  # Art 9.c — Final Report (within 15 calendar days)
  Scenario: Final incident report includes all required fields
    Given path '/posts'
    And request { incident_id: 'INC-2026-001', report_type: 'final', detailed_description: 'Credential stuffing attack targeting user login endpoint. 12 accounts accessed.', threat_type: 'credential_stuffing', mitigation_measures: 'Enforced MFA, rate limiting, blocked IP ranges via WAF', cross_border_impact: 'Attack from RU(8), CN(3), BR(1). No exfiltration.', conclusion: 'Incident contained. Users notified. Police report filed.', lessons_learned: 'Account lockout after 3 attempts, geo-blocking, 90-day password expiry' }
    When method POST
    Then status 201

  # Art 9 (OIV) — Plan de Acción (within 7 calendar days)
  Scenario: OIV action plan is submitted within 7 days
    Given path '/posts'
    And request { incident_id: 'INC-2026-001', plan_version: '1.0', submitted_at: '#string', actions: 'MFA enforcement — 48h, Rate limiting — 24h, Postmortem — 72h', owner: 'csirt-team@org.cl', deadline: '#string', status: 'approved' }
    When method POST
    Then status 201

  # Art 55 — Safe Harbor / Bug Bounty Disclosure
  Scenario: Vulnerability disclosure meets safe harbor conditions
    Given path '/posts'
    And request { researcher_id: 'RSRCH-0042', vulnerability_type: 'XSS', endpoint: '/search', description: 'Reflected XSS in search parameter', poc: '<script>alert(document.domain)</script>', disclosure_date: '#string', safe_harbor: true, consent_obtained: true, reported_to_anci: true }
    When method POST
    Then status 201
