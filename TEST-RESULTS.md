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

## Badges

```
[![Playwright](https://github.com/Signaltree/qa-automation-portfolio/actions/workflows/playwright.yml/badge.svg)](https://github.com/Signaltree/qa-automation-portfolio/actions/workflows/playwright.yml)
```
