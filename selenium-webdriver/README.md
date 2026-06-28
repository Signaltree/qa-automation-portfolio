# Selenium WebDriver — Standalone Test Suite

22 tests across 6 categories, testing real browser interactions on [SauceDemo](https://www.saucedemo.com) and [the-internet](https://the-internet.herokuapp.com).

## Tests

| Category | Tests | What it covers |
|----------|-------|----------------|
| Navigation & Waits | 4 | Nav, explicit/implicit/fluent waits |
| Multi-browser | 3 | Chrome, Firefox, Edge headless |
| Advanced Interaction | 4 | Drag-and-drop, hover, keyboard, alerts |
| Windows & Frames | 4 | Windows, tabs, iframes, nested frames |
| JavaScript | 4 | ScrollIntoView, DOM modify, async, metrics |
| Page Object Model | 3 | Login, cart, multiple items via POM |

## Run

```bash
cd selenium-webdriver
mvn test
```
