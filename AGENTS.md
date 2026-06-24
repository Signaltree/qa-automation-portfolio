# QA Automation Portfolio

This is a multi-framework QA automation portfolio with Playwright (TypeScript), and soon Karate (Java), Selenium, REST Assured, and BDD.

## Project Structure

```
qa-automation-portfolio/
├── playwright/              # Playwright test suite (TypeScript)
│   ├── tests/
│   │   ├── ui/              # UI functional tests
│   │   ├── api/             # Network interception & mocking
│   │   └── visual/          # Visual regression tests
│   ├── pages/               # Page Object Models
│   ├── fixtures/            # Test data (test-data.ts)
│   └── utils/               # Helper utilities
├── .opencode/
│   ├── skills/              # OpenCode skills (17 total)
│   │   ├── security-review  # Security orchestrator
│   │   ├── owasp-top-10     # OWASP vulnerability checks
│   │   ├── secret-detection # Hardcoded secrets scanner
│   │   ├── project-context  # Cross-session memory (saves tokens)
│   │   ├── session-memory   # Session state management
│   │   └── ...              # 12 more skills
│   └── commands/
│       └── learn.md         # /learn custom command
└── .github/workflows/       # CI/CD pipelines
```

## Key Commands

```bash
npm test                 # Run Playwright tests (Chromium)
npm run test:firefox     # Firefox
npm run test:webkit      # Safari
npm run test:mobile      # Mobile viewports
npm run test:api         # API interception tests
npm run test:visual      # Visual regression tests
npm run report           # Open HTML report
```

## Architecture

- **Pattern**: Page Object Model — all page interactions via `playwright/pages/`
- **Target**: SauceDemo (https://www.saucedemo.com) — SPA, no actual API calls
- **Data**: All test data centralized in `playwright/fixtures/test-data.ts`
- **Config**: Cross-browser config in `playwright.config.ts` (5 projects)
- **CI**: GitHub Actions with matrix builds

## Testing Standards

- Test names follow: `should [expected behavior]`
- Each describe block tests one feature
- Test data comes from fixtures, never hardcoded in specs
- Page Objects contain NO assertions (tests own the assertions)
- Visual tests have maxDiffPixelRatio: 0.05
- API tests cover interception, blocking, and timing

## Token-Saving Conventions

Before making changes, read `CONTEXT.md` if it exists to understand current project state. Use the `project-context` skill to persist and retrieve context across sessions.
