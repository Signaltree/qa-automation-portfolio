# CI/CD Pipeline

## GitHub Actions Workflows

### Playwright Tests (`playwright.yml`)

Triggered on push/PR to main. Runs matrix across 3 browsers:

```yaml
strategy:
  matrix:
    project: [chromium, firefox, webkit]
```

Steps:
1. Checkout + Node 22 setup
2. Install dependencies (`npm ci`)
3. Install Playwright browsers (`npx playwright install --with-deps ${{ matrix.project }}`)
4. Run tests (`npx playwright test --project=${{ matrix.project }}`)
5. Upload artifacts (HTML report, screenshots, videos)

### Karate API Tests (planned)

Triggered on push to `karate/` paths. Steps:
1. Checkout + JDK 21 setup
2. Maven build (`mvn test -pl karate`)
3. Publish test results

### Load Tests (manual)

```bash
mvn gatling:test -Pload-test -pl karate
```

## Badges

[![Playwright Tests](https://github.com/Signaltree/qa-automation-portfolio/actions/workflows/playwright.yml/badge.svg)](https://github.com/Signaltree/qa-automation-portfolio/actions/workflows/playwright.yml)
