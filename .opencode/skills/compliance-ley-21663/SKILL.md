---
description: Ley 21.663 (Chilean Cybersecurity Framework) — compliance test mapping for QA automation
---

# Compliance Mapping: Ley 21.663 → Automated Tests

## Overview

Ley 21.663 (Ley Marco de Ciberseguridad, published 2024-04-08) establishes Chile's national
cybersecurity framework. This skill maps each relevant article to automated QA tests in this portfolio.

## Key Principles (Art 3)

| Principle | Test Coverage | Location |
|-----------|--------------|----------|
| **3.6 — Derecho a cifrado** (Right to encryption) | TLS enforcement, HTTPS-only tests | `playwright/tests/security/security-headers.spec.ts` |
| **3.8 — Seguridad por defecto** (Security by default) | Default config audit, CSP, cookie attributes | `playwright/tests/security/security-headers.spec.ts` |
| **3.8 — Privacidad por defecto** (Privacy by design) | Data minimization, PII exposure checks | `playwright/tests/security/security-headers.spec.ts` |

## Obligations (Art 7-9)

| Article | Obligation | Test Type | Location |
|---------|-----------|-----------|----------|
| **7** | Medidas permanentes de prevención | Security headers, WAF rules, rate limiting | `karate/src/test/resources/features/security/security.feature:A05-01` |
| **8.a** | ISMS — risk management | Risk scoring, threat model validation | `karate/src/test/resources/features/security/security.feature:A09-01` |
| **8.b** | Security action registry | Audit log format, response time logging | `karate/src/test/resources/features/security/security.feature:A09-01` |
| **8.c** | BC/DR plans | Recovery tests — RTO/RPO validation | `karate/src/test/resources/features/load/api-simulation.feature` |
| **8.d** | Continuous review | Vulnerability scanning, version exposure | `karate/src/test/resources/features/security/security.feature:A06-01` |
| **8.e** | Impact reduction | Path traversal containment, SSRF blocking | `karate/src/test/resources/features/security/security.feature:A01-01, A10-01` |
| **8.g** | Breach notification | Notification workflow, format compliance | `karate/src/test/resources/features/security/csirt-reporting-contract.feature` |
| **9.a** | Alert within 3h | Pipeline latency SLO, alert generation | `karate/src/test/resources/features/security/csirt-reporting-contract.feature` |
| **9.b** | Update within 72h/24h | Status update schema, enrichment pipeline | `karate/src/test/resources/features/security/csirt-reporting-contract.feature` |
| **9.c** | Final report within 15d | Report schema validation, 4 required fields | `karate/src/test/resources/features/security/csirt-reporting-contract.feature` |
| **9 (OIV)** | Action plan within 7d | Plan submission, deadline enforcement | `karate/src/test/resources/features/security/csirt-reporting-contract.feature` |

## Vulnerability Disclosure (Art 19 + 55)

| Article | Requirement | Test | Location |
|---------|-------------|------|----------|
| **19** | Notificación responsable | Security contact discovery | `karate/src/test/resources/features/security/security.feature:L21663-19-01` |
| **55** | Safe harbor conditions | Disclosure schema, consent, ANCI registration | `karate/src/test/resources/features/security/csirt-reporting-contract.feature` |

## Definitions (Art 2)

| Term | Tests |
|------|-------|
| **Activo informático** | PII exposure, data classification |
| **Autenticación** | JWT validation, token rejection, auth bypass |
| **Confidencialidad** | TLS, HTTPS, cookie security, CORS |
| **Integridad** | SQLi prevention, path traversal, deserialization |
| **Disponibilidad** | Rate limiting, load testing, response time SLAs |

## Sanctions Matrix (Tít VII — Art 37-40)

| Severity | Fine (UTM) | Fine (USD approx) |
|----------|-----------|-------------------|
| Leves | Up to 5,000 (OIV: 10,000) | ~$32K–$64K |
| Graves | Up to 10,000 (OIV: 20,000) | ~$64K–$128K |
| Gravísimas | Up to 20,000 (OIV: 40,000) | ~$128K–$256K |

## Guidance for New Tests

When adding a new test that relates to Ley 21.663:

1. Identify which article(s) the test addresses
2. Add an annotation in Playwright: `test.info().annotations.push({ type: 'Ley 21663', description: 'Art X (Nombre) — descripción' })`
3. Include the article reference in the Scenario name: `L21663-XX-YY`
4. Add a comment in the .feature file: `# Ley 21.663 Art X: ...`
5. Map the test in this compliance matrix
