# Project Context

Last updated: 2026-06-24
Last session: Karate API tests (48 passing) + Gatling load testing + Ley 21.663 security compliance

## Current State

### Playwright (TypeScript) — 34 tests, all passing
- [DONE] UI functional tests (login, inventory, cart, checkout)
- [DONE] Network interception (JS bundle modification, CSS/image blocking, timing)
- [DONE] Visual regression (5 snapshot sets across viewports)
- [DONE] CI/CD: `.github/workflows/playwright.yml` (matrix: chromium, firefox, webkit)

### Karate (Java) — 48 API tests + Gatling load test, all passing
- [DONE] `posts.feature` — 9 scenarios (CRUD, filtering, empty body)
- [DONE] `users.feature` — 7 scenarios (schema validation, query params, headers)
- [DONE] `auth.feature` — 8 scenarios (error codes, malformed bodies, CORS, bulk validation)
- [DONE] `LoadTest.scala` — Gatling simulation (rampUsers 10→20, constantUsersPerSec 5, 120s max)
- [DONE] Load test profile: `mvn gatling:test -Pload-test`
- [DONE] Gatling 3.9.5 (Karate 1.4.1 compat), Scala 2.13.15, netty-tcnative-boringssl-static

### OpenCode Skills Ecosystem — 19+ skills
- [DONE] 5 categories: Security (6), Architecture (2), Code Quality (4), Optimization (5), Testing (3)
- [DONE] Context memory: `/learn` command, `AGENTS.md`, `CONTEXT.md`, `project-context` skill, `session-memory` skill
- [DONE] Plugins in `opencode.json` (supermemory, context-pruning, vibeguard, notificator)
- [NEW] `compliance-ley-21663` skill — Chilean cybersecurity law → test mapping
- [NEW] `security-testing` skill — OWASP + security test patterns

### Security & Compliance (NEW — session focus)
- [DONE] Ley 21.663 (Chilean Cybersecurity Framework Law) analysis
- [DONE] Art 2 → definitions mapped to test types (CIA triad, authentication, vulnerability scanning)
- [DONE] Art 7 → permanent prevention measures (WAF rules, CSP, continuous scanning)
- [DONE] Art 8 → ISMS, BC/DR, continuous review, incident response automation tests
- [DONE] Art 9 → reporting deadlines (3h/72h/15d/7d) mapped to pipeline latency SLO tests
- [DONE] Art 19+55 → vulnerability disclosure, safe harbor, bug bounty workflow tests
- [DONE] Art 3 → security & privacy by design/default (TLS, encryption, PII minimization)
- [DONE] Tít VII → sanctions matrix (up to 40,000 UTM for OIV gravísimas)
- [DONE] `security-payloads.json` — XSS, SQLi, NoSQLi, command injection, JWT tampering, path traversal
- [DONE] `security.feature` — Karate OWASP tests (17 scenarios: SQLi, XSS, JWT, CSRF, rate limit, CORS, etc.)
- [DONE] Playwright `security-headers.spec.ts` — CSP, HSTS, X-Frame-Options, cookies, CORS preflight
- [DONE] Contract test: `csirt-reporting-contract.feature` — mock CSIRT Nacional API submission

## Architecture Decisions

- **Target App**: SauceDemo (SPA, no real API — login is client-side JS)
- **Test Pattern**: Page Object Model — pages in `playwright/pages/`, tests in `playwright/tests/`
- **Test Data**: Centralized in `playwright/fixtures/test-data.ts` — 6 user types
- **Browser Config**: 5 projects in `playwright.config.ts` (Chromium, Firefox, WebKit, mobile)
- **CI**: GitHub Actions with 3-browser matrix, HTML report artifacts
- **Skills**: `.opencode/skills/` — skills aligned to Ley 21.663 compliance domains
- **Plugins**: Configured in `opencode.json` (supermemory, context-pruning, vibeguard, notificator)
- **Context**: `CONTEXT.md` for cross-session memory — managed by `/learn` command
- **Security**: Tests map to specific articles of Ley 21.663; payloads are parameterized data-driven
- **Load**: Gatling simulation uses Scala (Karate-Gatling ActionBuilder is Scala-native)
- **Gatling Version**: Karate 1.4.1 ships with Gatling 3.9.5 (NOT 3.11.x — causes NoSuchMethodError)

## Gotchas

- SauceDemo inventory data is in `main.bcf4bc5f.js` (JS bundle), NOT in HTML
- No POST requests exist — the SPA handles login client-side
- Form submission interception doesn't work; use JS bundle route interception instead
- Visual snapshots committed as baselines — update with `npm run test:update-snapshots`
- Karate `match` does NOT support multi-line JSON in feature files (use single-line or `def` + separate checks)
- `responseHeaders` in Karate is `Map<String, List<String>>` — access via `responseHeaders['Header-Name'][0]`
- Karate `match $.length > 0` not supported — use `assert karate.sizeOf(response) > 0`
- `mvn gatling:test -Pload-test` runs Gatling (NOT `mvn test -Pload-test`)
- Scala 2.13.15 needed for JDK 21 compatibility (2.13.9 has constant pool bug)
- netty-tcnative-boringssl-static required for Gatling SSL on linux_x86_64

## Next Steps

1. Add Selenium WebDriver (Java) tests
2. Add REST Assured API tests
3. Add BDD with Cucumber/SpecFlow
4. Push all uncommitted changes to GitHub
5. Add Karate CI/CD workflow (`.github/workflows/karate.yml`)
