# Test Results / Resultados de Pruebas

## Playwright (34 tests)

| Browser | UI | API Interception | Visual | Security | Total |
|---------|----|------------------|--------|----------|-------|
| Chromium | ✅ | ✅ | ✅ | ✅ | 34/34 |
| Firefox | ✅ | ✅ | ⚠️ (snapshots pending) | ✅ | 30/34 |
| WebKit | ⚠️ (deps) | ⚠️ | ⚠️ | ⚠️ | — |

## Karate (67 API tests)

| Suite | Tests | Passing |
|-------|-------|---------|
| Posts API | 9 | ✅ 9/9 |
| Users API | 7 | ✅ 7/7 |
| Auth API | 8 | ✅ 8/8 |
| Security (OWASP) | 14 | ✅ 14/14 |
| CSIRT Contract (Ley 21.663) | 5 | ✅ 5/5 |
| Load Simulation | 6 | ✅ 6/6 |
| Gatling Load | 1 simulation | ✅ Passes |

## Selenium WebDriver (22 tests)

| Category | Tests | Passing |
|----------|-------|---------|
| Navigation & Waits | 4 | ✅ |
| Multi-browser | 3 | ✅ |
| Advanced Interaction | 4 | ✅ |
| Windows & Frames | 4 | ✅ |
| JavaScript | 4 | ✅ |
| Page Object Model | 3 | ✅ |

## Cypress (19 tests)

| Spec | Tests | Passing |
|------|-------|---------|
| login.cy.js | 3 | ✅ 3/3 |
| inventory.cy.js | 4 | ✅ 4/4 |
| cart.cy.js | 4 | ✅ 4/4 |
| checkout.cy.js | 4 | ✅ 4/4 |
| burger-menu.cy.js | 2 | ✅ 2/2 |
| error-states.cy.js | 2 | ✅ 2/2 |

## Flutter (7 integration tests)

| Test | Status |
|------|--------|
| App renders with title | ✅ |
| Counter starts at 0 | ✅ |
| Increment button works | ✅ |
| Multiple increments | ✅ |
| Add todo item | ✅ |
| Toggle todo completion | ✅ |
| Delete todo item | ✅ |

## Badges

```
[![Playwright](https://github.com/Signaltree/qa-automation-portfolio/actions/workflows/playwright.yml/badge.svg)](https://github.com/Signaltree/qa-automation-portfolio/actions/workflows/playwright.yml)
```
