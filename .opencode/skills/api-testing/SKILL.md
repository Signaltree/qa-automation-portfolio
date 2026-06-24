---
name: api-testing
description: Guides API testing patterns including contract, integration, and E2E tests
license: MIT
compatibility: opencode
metadata:
  audience: qa, developers
  workflow: review
---

## What I do
Guide API testing implementation and best practices:

### API Test Types
- **Contract Tests** — Verify API contract (status codes, response shape)
- **Integration Tests** — Test real interactions with the API
- **Negative Tests** — Invalid inputs, unauthorized access, missing fields
- **Edge Case Tests** — Empty responses, large payloads, special characters
- **Schema Validation** — Response body matches expected schema

### Test Structure
- Separate API tests from UI tests
- Use data providers / parameterized tests for multiple scenarios
- Test setup creates required data, teardown cleans up
- Chain requests to test workflows (create → read → update → delete)

### Assertions
- Status codes (2xx success, 4xx client errors, 5xx server errors)
- Response body structure (JSON schema validation)
- Response headers (Content-Type, CORS, caching)
- Response time (performance SLAs)
- Error response format (consistent error structure)

## Tools & Frameworks (recommended)
- **Playwright APIRequestContext** — Built-in API testing in Playwright
- **Karate** — DSL for API testing with built-in assertions
- **REST Assured** — Java DSL for REST API testing
- **Supertest** — Node.js HTTP assertion library

## When to use me
Use this skill when setting up API test suites, writing new endpoint tests, or reviewing API test coverage.

## API test checklist
- [ ] Happy path tested (200/201)
- [ ] 400 Bad Request for invalid input
- [ ] 401 for unauthenticated access
- [ ] 403 for unauthorized access
- [ ] 404 for non-existent resources
- [ ] 409 for conflict (duplicate)
- [ ] Schema validation for success responses
- [ ] Response time within SLA
