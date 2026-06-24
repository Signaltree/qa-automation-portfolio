---
name: performance-review
description: Reviews test and application performance patterns
license: MIT
compatibility: opencode
metadata:
  audience: developers, qa
  workflow: review
---

## What I do
Analyze performance characteristics of both application code and test suites:

### Test Performance
- Slow tests (identify >1s tests and recommend optimization)
- Flaky tests (network-dependent, time-based, shared state)
- Parallelization opportunities
- Setup/teardown overhead
- Resource cleanup (browser contexts, DB connections)
- Timeout appropriateness

### Application Performance (for test targets)
- Page load time under automation
- API response time distribution
- Resource loading waterfalls
- Memory consumption trends
- Rendering performance (CLS, LCP, FID)

### Load Testing Readiness
- Test data volume handling
- Concurrent user simulation
- Assertions under load (response time SLAs)
- Performance regression detection

## When to use me
Use this skill when optimizing slow tests, before load testing campaigns, or when investigating test flakiness.

## Performance budgets (suggested)
- Individual test: < 5s
- Full suite (parallel): < 10min
- Page navigation: < 3s
- API response (p95): < 500ms
