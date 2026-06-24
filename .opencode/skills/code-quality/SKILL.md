---
name: code-quality
description: Ensures code follows best practices for TypeScript, Playwright, and maintainability
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: review
---

## What I do
- Enforce consistent naming conventions for Page Objects, tests, and utilities
- Verify TypeScript strict mode and proper typing across the project
- Check that tests are readable, deterministic, and isolated
- Validate that Page Object methods return meaningful values for assertion
- Ensure no hardcoded values in tests (use fixtures/test-data.ts instead)
- Check that async/await is used correctly and consistently
- Verify that locators use Playwright best practices (not XPath when CSS suffices)
- Confirm tests have proper setup/teardown (beforeEach/afterEach)

## When to use me
Use this skill during code review, before committing new tests, or when refactoring existing code. I help maintain a consistent, high-quality codebase.

## Non-negotiables
1. TypeScript strict mode must be enabled
2. No test data hardcoded in spec files — use fixtures
3. Page Objects must not contain assertions (that is the test's job)
4. All async operations must use await
5. Tests must be independent and able to run in any order
