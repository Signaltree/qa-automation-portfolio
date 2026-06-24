# QA Automation Portfolio / Portafolio de Automatización QA

[![Playwright](https://img.shields.io/badge/Playwright-45%20tests-45ba4b?logo=playwright)](playwright/)
[![Karate](https://img.shields.io/badge/Karate-67%20tests-000?logo=java)](karate/)
[![Gatling](https://img.shields.io/badge/Gatling-Load%20testing-FFCB28?logo=scala)](karate/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-18%20tests-8A2BE2?logo=java)](rest-assured/)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-23D96C?logo=cucumber)](bdd-cucumber/)
[![Security](https://img.shields.io/badge/Security-OWASP%20%2B%20Ley%2021.663-8B0000)](playwright/tests/security/)

> **Multi-framework QA automation portfolio** demonstrating UI, API, load, security, and BDD testing across 5 frameworks, with AI-assisted development via OpenCode.

> **Portafolio de automatización QA multi-framework** que demuestra pruebas UI, API, carga, seguridad y BDD en 5 frameworks, con desarrollo asistido por IA via OpenCode.

---

## Quick Overview / Resumen Rápido

| Framework | Language | Tests | Target | Status |
|-----------|----------|-------|--------|--------|
| **Playwright** | TypeScript | 45 (UI + API + Visual + Security) | SauceDemo | ✅ All passing |
| **Karate** | Java | 67 API + Gatling load | JSONPlaceholder | ✅ All passing |
| **REST Assured** | Java | 18 API contract | JSONPlaceholder | ✅ All passing |
| **Cucumber BDD** | Java | 12 Gherkin + Selenium | SauceDemo | ✅ All passing |

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

### 🧪 [REST Assured — API Contract Testing](rest-assured/)
- **18 tests**: CRUD, JSON Schema validation, POJO serialization
- **Hamcrest matchers**: `every`, `hasItem`, `containsString`, `notNullValue`
- **Same API as Karate** (JSONPlaceholder) — direct framework comparison
- **Jackson POJOs**: Post, User + nested Address, Geo, Company

### 🥒 [Cucumber BDD — Selenium WebDriver](bdd-cucumber/)
- **12 Gherkin scenarios**: login, inventory, cart, checkout on SauceDemo
- **Selenium 4 + WebDriverManager**: ChromeDriver auto-management
- **Docker-ready**: Remote WebDriver via `-Dselenium.remote.url`

### 🐳 Docker + Selenium Grid
- `compose.yaml` spins up Hub + Chrome + Firefox nodes
- BDD tests can run inside container against the Grid
- `docker compose up` → `mvn test -f bdd-cucumber/pom.xml -Dselenium.remote.url=http://localhost:4444/wd/hub`

### 🤖 [AI-Assisted Development](.opencode/)
This entire project was built with **OpenCode** — an open-source AI coding agent — using **24 specialized skills** that collaborate like a QA council: architecture review, security auditing, performance review, testing standards, and more.

---

## Tech Stack / Stack Tecnológico

| Area | Technologies |
|------|--------------|
| **UI Testing** | Playwright, TypeScript, POM |
| **API Testing** | Karate (Cucumber-based), REST Assured (Hamcrest) |
| **Load Testing** | Gatling 3.9.5, Scala 2.13.15 |
| **Security** | OWASP ZAP-style scanning, Ley 21.663 compliance |
| **BDD** | Cucumber 7, Selenium 4, Gherkin |
| **CI/CD** | GitHub Actions (matrix builds + 4 workflows) |
| **Containerization** | Docker Compose, Selenium Grid |
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
├── playwright/          # 45 tests — UI, API interception, Visual, Security
├── karate/              # 67 API tests + Gatling load simulation
├── rest-assured/        # 18 API contract tests
├── bdd-cucumber/        # 12 Cucumber BDD + Selenium scenarios
├── docs/                # Architecture & CI/CD documentation
├── .github/workflows/   # 3 CI/CD pipelines (Playwright + Java + Docker)
├── compose.yaml         # Selenium Grid with Hub + Chrome + Firefox
├── .opencode/           # OpenCode AI skills & configuration
└── README.md            # You are here 📍
```

---

## Test Results / Resultados de Pruebas

| Suite | Count | Status |
|-------|-------|--------|
| Playwright UI | ~25 | ✅ |
| Playwright Security | 11 (×2 browsers) | ✅ |
| Playwright Visual | ~5 | ✅ |
| Playwright API | ~4 | ✅ |
| Karate API | 43 REST + 14 Security + 5 CSIRT + 6 Load | ✅ |
| Gatling Load | 6 scenarios | ✅ |
| REST Assured | 18 (CRUD + Schema + POJO) | ✅ |
| Cucumber BDD | 12 (Gherkin + Selenium) | ✅ |
| **Total** | **~135+** | ✅ |

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
