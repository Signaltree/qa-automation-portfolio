# Playwright — Web UI + API + Visual + Security Testing

**34 tests** | TypeScript | Page Object Model | Cross-browser

## What This Demonstrates

| Skill | Implementation |
|-------|----------------|
| **UI Functional** | Login, inventory, cart, checkout flows against SauceDemo |
| **Data-Driven** | 6 user types (standard, locked-out, problem, glitch, error, visual) |
| **Page Object Model** | 4 POM classes with clean separation of concerns |
| **Cross-Browser** | Chromium, Firefox, WebKit, mobile-Chrome, mobile-Safari |
| **API Interception** | JS bundle modification, CSS blocking, request timing |
| **Visual Regression** | Screenshot comparison with 5% diff threshold |
| **Security Testing** | CSP, HSTS, CORS, SQLi, XSS, path traversal, cookie audit |
| **Compliance** | Ley 21.663 (Chilean Cybersecurity Framework) annotations |

## Test Structure

```
playwright/
├── tests/
│   ├── ui/           login.spec.ts, inventory.spec.ts
│   ├── security/     security-headers.spec.ts (55 test variations)
│   ├── api/          network-interception.spec.ts
│   └── visual/       visual.spec.ts
├── pages/            LoginPage, InventoryPage, CartPage, CheckoutPage
├── fixtures/         test-data.ts (6 user types)
└── utils/            helpers.ts
```

## Run

```bash
npx playwright test --project=chromium   # 34 tests
npx playwright test tests/security       # Security only
npx playwright test tests/visual         # Visual regression
```
