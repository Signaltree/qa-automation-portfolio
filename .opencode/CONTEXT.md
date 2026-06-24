## Current State
- Playwright: 45 tests all passing (Chromium + Firefox)
- Karate: 67 API tests + Gatling load — all passing
- REST Assured: 18 API contract tests — all passing
- Cucumber BDD: 12 scenarios — all passing (uses system Chromium)
- Total: ~135+ tests across 4 frameworks
- Docker Compose: Selenium Grid (Hub + Chrome + Firefox nodes)
- CI/CD: 3 workflows (playwright.yml, java-tests.yml)

## Recent Changes (Jun 24)
- Created REST Assured project (18 tests, POJOs, JSON Schema, Hamcrest)
- Created java-tests.yml CI workflow (Karate + REST Assured + BDD)
- Updated playwright.yml (removed WebKit, Chromium + Firefox only)
- Created compose.yaml (Selenium Grid + Docker Compose)
- Updated Hooks.java to support RemoteWebDriver via -Dselenium.remote.url
- Updated README with all 4 frameworks, badges, Docker section
- Pinned ChromeDriver to v149 (matches system Chromium)

## Architecture
- **Root**: README.md, playwright/, karate/, rest-assured/, bdd-cucumber/, docs/, .github/, compose.yaml, .opencode/
- **Playwright**: POM (4 pages), 45 tests, 2 browsers (Chromium + Firefox), SauceDemo target
- **Karate**: 67 API tests + Gatling, JSONPlaceholder target, Maven + Scala
- **REST Assured**: 18 API contract tests, JSONPlaceholder, Jackson POJOs
- **BDD**: Cucumber 7 + Selenium 4 + WebDriverManager, SauceDemo target
- **All Java projects**: JDK 21, standalone pom.xml, JUnit 5
- **CI/CD**: 3 GitHub Actions workflows + Docker Compose for Selenium Grid
- **Skills**: 24 OpenCode skills in 5 categories
