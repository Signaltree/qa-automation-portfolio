# QA Automation Portfolio / Portafolio de Automatización QA

[![Playwright](https://img.shields.io/badge/Playwright-34%20tests-45ba4b?logo=playwright)](playwright/)
[![Karate](https://img.shields.io/badge/Karate-67%20tests-000?logo=java)](karate/)
[![Gatling](https://img.shields.io/badge/Gatling-Load%20testing-FFCB28?logo=scala)](karate/)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-23D96C?logo=cucumber)](bdd-cucumber/)
[![Security](https://img.shields.io/badge/Security-OWASP%20%2B%20Ley%2021.663-8B0000)](playwright/tests/security/)

> **Multi-framework QA automation portfolio** demonstrating UI, API, load, security, and BDD testing across 5 frameworks, with AI-assisted development via OpenCode.

> **Portafolio de automatización QA multi-framework** que demuestra pruebas UI, API, carga, seguridad y BDD en 5 frameworks, con desarrollo asistido por IA via OpenCode.

---

## Quick Overview / Resumen Rápido

| Framework | Language | Tests | Target | Status |
|-----------|----------|-------|--------|--------|
| **Playwright** | TypeScript | 34 (UI + API + Visual + Security) | SauceDemo | ✅ All passing |
| **Karate** | Java | 67 API + Gatling load | JSONPlaceholder | ✅ All passing |
| **Cucumber BDD** | Java | *Coming soon* | — | 🚧 In progress |

---

## What You'll Find / Lo Que Encontrarás

### 🎭 [Playwright — Web UI & Visual Testing](playwright/)
- **Page Object Model** with 4 page classes (Login, Inventory, Cart, Checkout)
- **Cross-browser**: Chromium, Firefox, WebKit, mobile-Chrome, mobile-Safari
- **Data-driven**: 6 user types from centralized fixtures
- **Network interception**: JS bundle modification, CSS blocking, request timing
- **Visual regression**: Screenshot comparison with 5% diff threshold
- **CI/CD**: GitHub Actions matrix builds with artifact upload

### 🥋 [Karate — API + Load + Security Testing](karate/)
- **67 API tests**: CRUD, schema validation, negative testing on JSONPlaceholder
- **Security**: OWASP Top 10 (XSS, SQLi, JWT tampering, SSRF, XXE, CORS, CSRF) — 14 scenarios
- **Compliance**: Ley 21.663 (Chilean Cybersecurity Framework) — Art 9 CSIRT contract tests
- **Load testing**: Gatling 3.9.5 simulation (rampUsers, constantUsersPerSec)
- **60+ security payloads**: Comprehensive injection payload dataset

### 🥒 Cucumber BDD (Coming Soon)
- Gherkin feature files + Serenity BDD reports
- Living documentation with scenario overviews

### 🤖 [AI-Assisted Development](.opencode/)
This entire project was built with **OpenCode** — an open-source AI coding agent — using **24 specialized skills** that collaborate like a QA council: architecture review, security auditing, performance review, testing standards, and more.

---

## Tech Stack / Stack Tecnológico

| Area | Technologies |
|------|--------------|
| **UI Testing** | Playwright, TypeScript, POM |
| **API Testing** | Karate (Cucumber-based), REST Assured (planned) |
| **Load Testing** | Gatling 3.9.5, Scala 2.13.15 |
| **Security** | OWASP ZAP-style scanning, Ley 21.663 compliance |
| **BDD** | Cucumber, Serenity (planned) |
| **CI/CD** | GitHub Actions (matrix builds) |
| **AI Assistant** | OpenCode with 24 specialized skills |
| **Languages** | TypeScript, Java 21, Scala, Gherkin |

---

## Quick Start / Inicio Rápido

```bash
# Playwright tests
npm install
npx playwright install chromium
npm test

# Karate API tests
cd karate
mvn test

# Karate load tests
mvn gatling:test -Pload-test
```

---

## Project Structure / Estructura del Proyecto

```
qa-automation-portfolio/
├── playwright/          # 34 tests — UI, API interception, Visual, Security
├── karate/              # 67 API tests + Gatling load simulation
├── bdd-cucumber/        # Cucumber BDD (coming soon)
├── docs/                # Architecture & CI/CD documentation
├── .github/workflows/   # GitHub Actions CI/CD pipelines
├── .opencode/           # OpenCode AI skills & configuration
└── README.md            # You are here 📍
```

---

## Test Results / Resultados de Pruebas

| Suite | Count | Status |
|-------|-------|--------|
| Playwright UI | ~20 | ✅ |
| Playwright Security | 11 (×5 browsers) | ✅ |
| Playwright Visual | ~5 | ✅ |
| Playwright API | ~5 | ✅ |
| Karate API | 43 REST + 14 Security + 5 CSIRT + 6 Load | ✅ |
| Gatling Load | 6 scenarios | ✅ |
| **Total** | **~100+** | ✅ |

Detailed results: [TEST-RESULTS.md](TEST-RESULTS.md)

---

## Key Features / Características Clave

- ✅ **Multi-framework**: 5 testing frameworks demonstrating breadth
- ✅ **Real targets**: SauceDemo, JSONPlaceholder (not toy apps)
- ✅ **Security-first**: OWASP Top 10 + Chilean cybersecurity law compliance
- ✅ **AI-assisted**: Built with OpenCode — 24 skills, test-driven development
- ✅ **CI/CD ready**: GitHub Actions with matrix builds and artifact upload
- ✅ **Bilingual**: English + Spanish documentation
- ✅ **Clean code**: POM, data-driven, centralized fixtures, no hardcoded values

---

## License

MIT — see [LICENSE](LICENSE)

---

*Built with [OpenCode](https://opencode.ai) — the open source AI coding agent.*
