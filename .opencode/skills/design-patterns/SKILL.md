---
name: design-patterns
description: Recommends and validates design pattern usage across the project
license: MIT
compatibility: opencode
metadata:
  audience: developers, architects
  workflow: review
---

## What I do
Review design pattern usage and recommend appropriate patterns:

### Common Patterns in QA Automation
- **Page Object Model** — Verify correct implementation (pages != tests)
- **Factory Pattern** — For creating test data objects
- **Builder Pattern** — For complex test data construction
- **Strategy Pattern** — For different test approaches (API vs UI)
- **Facade Pattern** — Simplifying complex page interactions
- **Singleton** — For shared resources (DB connections, API clients)
- **Fluent Interface** — For readable test assertions

### Pattern Correctness Checks
- Single Responsibility: Pages should not contain assertions
- Open/Closed: Tests should be extensible without modifying pages
- Dependency Inversion: Tests depend on abstractions (pages), not details
- Composition over inheritance in Page Objects

## When to use me
Use this skill when designing new test frameworks, refactoring existing page objects, or reviewing pattern usage.

## Anti-patterns to flag
- God Page Objects (too many responsibilities)
- Tests going directly to DOM selectors (bypassing Page Objects)
- Shared mutable state between tests
- Over-engineering (patterns where simple code suffices)
- Fragile tests (tight coupling to implementation details)
