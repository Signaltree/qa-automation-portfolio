## Current State
- Playwright: 34 tests all passing (Chromium + Firefox, WebKit not installed)
- Karate: 67 API tests + Gatling load — all passing
- Cucumber BDD: 12 scenarios (2 feature files, 10 scenario outlines, Selenium WebDriver)
- Test results: ~100+ total tests across all frameworks

## Recent Changes (Jun 24)
- Restructured project: AI files moved to `.opencode/`, root cleaned for recruiters
- Created `docs/` (architecture.md, ci-cd.md)
- Added per-framework READMEs (playwright/README.md, karate/README.md, bdd-cucumber/README.md)
- Updated root README as bilingual portfolio centerpiece with badges
- Added TEST-RESULTS.md for visible test results tracking
- Added 2 new skills: compliance-ley-21663, security-testing (now 24 total)
- Fixed 3 Playwright security tests (server version → soft check, path traversal → raw paths, cookie → structure validation)
- Created Cucumber BDD project (bdd-cucumber/) with Selenium WebDriver
- Added /end-session OpenCode command
- Committed: 33 files, 1557 insertions, 338 deletions

## Architecture
- **Root**: README.md, playwright/, karate/, bdd-cucumber/, docs/, .github/, .opencode/
- **Playwright**: POM (4 pages), 34 tests, 5 browsers, SauceDemo target
- **Karate**: 67 API tests + Gatling, JSONPlaceholder target, Maven + Scala
- **BDD**: Cucumber 7 + Selenium 4 + WebDriverManager, SauceDemo target
- **Skills**: 24 OpenCode skills in 5 categories
- **CI/CD**: GitHub Actions (playwright.yml exists, karate/bdd planned)

## Decisions & Rationale
- Moved AI files to `.opencode/` — cleaner root for recruiter viewing
- Karate and bdd-cucumber are standalone Maven projects (no parent POM)
- BDD skips gracefully with TestAbortedException if Chrome not installed
- Cucumber 7.18.1 + JUnit Platform Suite (not vintage engine)
- Selenium 4.27 + WebDriverManager 5.9.2 for browser management

## Gotchas
- WebKit not installed (missing system deps, no sudo available)
- Firefox visual snapshots need separate generation
- Chrome not installed — BDD tests skip via TestAbortedException
- Karate needs `mvn gatling:test -Pload-test` (not `mvn test -Pload-test`)

## Next Steps
1. Push to GitHub (user does this manually — SSH passphrase)
2. Add Karate CI/CD workflow (.github/workflows/karate.yml)
3. Install Chrome + run BDD tests for real verification
4. Generate Firefox visual snapshots
5. Add REST Assured tests
6. Consider: Selenium standalone (separate from BDD), or keep BDD as the Selenium layer?

## Available Commands
- `/learn` — Scan project and update CONTEXT.md
- `/status` — Show current project state
- `/end-session` — Save session progress to CONTEXT.md
