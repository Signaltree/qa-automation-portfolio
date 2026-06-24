# Architecture / Arquitectura

## Overall Design

This portfolio demonstrates QA automation across multiple frameworks and layers:

```
┌─────────────────────────────────────────────────────────┐
│                    CI/CD (GitHub Actions)                │
├─────────────────────────────────────────────────────────┤
│  Layer 5: BDD (Cucumber)         — Behavior-driven      │
│  Layer 4: REST Assured            — Contract/API tests   │
│  Layer 3: Selenium WebDriver     — Java browser tests    │
│  Layer 2: Karate                  — API + Load + Security │
│  Layer 1: Playwright              — UI + Visual + API     │
├─────────────────────────────────────────────────────────┤
│                   OpenCode AI Skills                     │
└─────────────────────────────────────────────────────────┘
```

## Layer 1: Playwright (TypeScript)

**Target**: SauceDemo (Sauce Labs Swag Labs) — React SPA
**Pattern**: Page Object Model
**Tests**: 34 (UI functional, API interception, visual regression, security)
**Browsers**: Chromium, Firefox, WebKit, mobile-Chrome, mobile-Safari

Key design decisions:
- Login is client-side (no HTTP POST) — all inventory data in JS bundle `main.bcf4bc5f.js`
- `test.use({ storageState })` skipped due to SauceDemo session cookie behavior
- Visual regression uses `maxDiffPixelRatio: 0.05` threshold
- Network interception tests modify JS bundles and block CSS in-flight

## Layer 2: Karate (Java)

**Target**: JSONPlaceholder (REST API)
**Tests**: 67 API tests + Gatling load simulation
**Frameworks**: Karate 1.4.1, JUnit 5.10.5, Gatling 3.9.5, Scala 2.13.15

Key design decisions:
- Karate 1.4.1 is the latest stable on Maven Central
- Gatling 3.9.5 required (Karate 1.4.1 compiled against 3.9.5)
- Scala 2.13.15 needed over transitive 2.13.9 (constant pool bug with JDK 21)
- `netty-tcnative-boringssl-static` required for Gatling SSL on linux_x86_64
- Security tests map to Ley 21.663 (Chilean Cybersecurity Framework) articles

## Security & Compliance

### OWASP Top 10 (2021) Coverage
- **A01**: Broken Access Control — path traversal, unauthorized access
- **A03**: Injection — XSS, SQLi, NoSQLi, command injection
- **A04**: Insecure Design — missing security headers
- **A05**: Security Misconfiguration — server version exposure, CORS
- **A07**: Identification Failures — auth bypass attempts
- **A08**: Integrity Failures — JWT tampering, CSRF

### Ley 21.663 (Chilean Cybersecurity Framework)
- **Art 2**: Confidentiality — cookie security attributes
- **Art 3**: Security measures — CSP, HSTS, XSS mitigation
- **Art 7**: Risk management — security headers review
- **Art 8.d**: Continuous review — monitor exposed versions
- **Art 8.e**: Impact reduction — path traversal containment
- **Art 9**: Incident reporting — CSIRT contract testing
