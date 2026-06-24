---
name: testing-standards
description: Enforces testing patterns, naming conventions, and coverage standards
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: review
---

## What I do
- Enforce test naming convention: `should [expected behavior]` format
- Verify that each describe block tests a single feature/component
- Ensure setup logic is in `beforeEach` rather than duplicated in each test
- Check for edge case coverage (empty states, error states, boundary values)
- Verify that visual tests include mobile and responsive viewports
- Confirm API interception tests cover modification, blocking, and timing
- Ensure tests clean up after themselves (no test pollution)
- Validate that test timeouts are set appropriately
- Check that the test runner config supports CI and local development

## When to use me
Use this skill when writing new test suites, reviewing PRs with test changes, or setting up testing standards for a new project.

## Testing checklist
- [ ] Tests follow `describe`/`test` nesting convention
- [ ] Test names are descriptive: `should X when Y`
- [ ] Edge cases are covered (empty, invalid, boundary)
- [ ] Visual tests have appropriate diff thresholds
- [ ] API tests include interception, blocking, and timing
- [ ] Mobile viewports are tested separately
- [ ] No test depends on another test's state
- [ ] Timeouts are set for slow operations (not the default for everything)
