# QA Automation Portfolio

Multi-framework QA automation portfolio: Playwright (TypeScript), Karate (API + Load + Security), Cucumber (BDD), and more.

## Project Structure

```
qa-automation-portfolio/
├── playwright/          # UI tests + API interception + Visual regression + Security
│   ├── tests/
│   │   ├── ui/          # SauceDemo login, inventory, cart, checkout
│   │   ├── security/    # Security headers, OWASP, Ley 21.663 compliance
│   │   ├── api/         # Network interception & mocking
│   │   └── visual/      # Visual regression (Chromium snapshots)
│   ├── pages/           # Page Object Models
│   ├── fixtures/        # Test data (6 user types)
│   └── utils/           # Helpers
├── karate/              # API testing + Load testing + Security
│   ├── src/
│   │   ├── test/java    # JUnit 5 runners
│   │   ├── test/scala   # Gatling load simulation
│   │   └── test/resources/
│   │       ├── features/api/       # REST API tests (posts, users, auth)
│   │       ├── features/security/  # OWASP + Ley 21.663 tests
│   │       ├── features/load/      # Gatling simulations
│   │       └── data/               # Test data + security payloads
│   └── pom.xml
├── bdd-cucumber/        # Cucumber + Serenity BDD (coming soon)
├── docs/                # Architecture & CI/CD documentation
├── .github/             # CI/CD workflows
├── .opencode/           # OpenCode AI tooling (skills, commands, config)
│   ├── skills/          # 24 specialized AI skills
│   ├── commands/        # /learn, /status, /end-session
│   ├── AGENTS.md        # These agent instructions
│   ├── CONTEXT.md       # Cross-session memory
│   └── opencode.json    # OpenCode configuration
└── README.md            # Bilingual portfolio centerpiece
```

## Key Commands

```bash
# Playwright
npm test                     # Chromium
npm run test:firefox         # Firefox
npm run test:webkit          # Safari
npm run test:security        # Security + compliance tests
npm run test:visual          # Visual regression

# Karate (from karate/)
mvn test                     # All API tests (67)
mvn test -Dtest=ApiTest#testSecurity  # Security only
mvn gatling:test -Pload-test          # Load test

# OpenCode
/learn         # Update cross-session memory
/status        # Show current project state
/end-session   # Save progress before stopping
```

## Architecture

- Playwright: Page Object Model, SauceDemo SPA, 34 tests across 5 browser projects
- Karate 1.4.1: 67 API tests + Gatling 3.9.5 load test, JSONPlaceholder target
- Security: OWASP Top 10 + Ley 21.663 (Chilean Cybersecurity Framework) compliance
- CI: GitHub Actions matrix builds (Chromium, Firefox, WebKit)

## Conventions

- Read `.opencode/CONTEXT.md` at session start before any other file
- Test names: `should [expected behavior]`
- Page Objects contain NO assertions
- Test data from fixtures, never hardcoded
