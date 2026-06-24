# Project Context

Last updated: 2026-06-24
Last session: Playwright test suite + OpenCode skills ecosystem

## Current State

- [DONE] Playwright test suite (34 tests, all passing)
- [DONE] Page Object Model (4 pages: Login, Inventory, Cart, Checkout)
- [DONE] Network interception tests (JS bundle modification, CSS/image blocking, timing)
- [DONE] Visual regression tests (5 snapshots across viewports)
- [DONE] CI/CD pipeline (GitHub Actions, matrix builds)
- [DONE] OpenCode skills ecosystem (17 skills, 5 categories)
- [DONE] Bilingual README (English + Spanish)
- [PENDING] Karate framework for API + load testing
- [PENDING] Selenium WebDriver (Java)
- [PENDING] REST Assured API tests
- [PENDING] BDD with Cucumber/SpecFlow

## Architecture Decisions

- **Target App**: SauceDemo (SPA, no real API — login is client-side JS)
- **Test Pattern**: Page Object Model — pages in `playwright/pages/`, tests in `playwright/tests/`
- **Test Data**: Centralized in `playwright/fixtures/test-data.ts` — 6 user types
- **Browser Config**: 5 projects in `playwright.config.ts` (Chromium, Firefox, WebKit, mobile)
- **CI**: GitHub Actions with 3-browser matrix, HTML report artifacts
- **Skills**: `.opencode/skills/` — 17 skills for security, architecture, code quality, optimization, testing
- **Plugins**: Configured in `opencode.json` (supermemory, context-pruning, vibeguard, notificator)
- **Context**: `CONTEXT.md` for cross-session memory — managed by `/learn` command

## Gotchas

- SauceDemo inventory data is in `main.bcf4bc5f.js` (JS bundle), NOT in HTML
- No POST requests exist — the SPA handles login client-side
- Form submission interception doesn't work; use JS bundle route interception instead
- Visual snapshots committed as baselines — update with `npm run test:update-snapshots`
- Errors in `intercept.spec.ts` were due to Form POST not being a real HTTP POST

## Next Steps

1. ~~Add Karate framework for API + load testing~~
2. Integrate Selenium WebDriver (Java) tests
3. Add REST Assured API tests
4. Add BDD with Cucumber/SpecFlow
