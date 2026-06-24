# Cucumber BDD — Behavior-Driven Development

**10 scenarios** | Java 21 | Selenium WebDriver | Gherkin | JUnit 5

## What This Demonstrates

| Skill | Implementation |
|-------|----------------|
| **Gherkin Syntax** | Feature files in Given-When-Then format |
| **Scenario Outlines** | Data-driven tests with Examples tables |
| **Page Objects** | Java POM classes (Login, Inventory, Cart, Checkout) |
| **Selenium Integration** | WebDriverManager, ChromeOptions, headless mode |
| **Cucumber Hooks** | @Before/@After for WebDriver lifecycle |
| **JUnit 5 Suite** | @Suite + @IncludeEngines for Cucumber runner |
| **Cucumber Reports** | HTML + JSON pretty output |
| **Tags** | @smoke, @cart, @checkout, @visual for selective runs |

## Feature Files

| File | Scenarios | Tags |
|------|-----------|------|
| `login.feature` | 6 (login, locked-out, invalid, sort, visual, checkout) | @smoke, @inventory, @visual |
| `inventory.feature` | 4 (add, remove, multiple, persistence) | @cart |

## Prerequisites

- Chrome browser installed (for WebDriver)
- Java 21

## Run

```bash
mvn test                          # All scenarios
mvn test -Dcucumber.filter.tags="@smoke"   # Smoke tests only
mvn test -Dcucumber.filter.tags="@cart"    # Cart tests only
```

Tests skip gracefully if Chrome is not installed.
```
