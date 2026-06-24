---
name: load-testing
description: Guides load, stress, and performance testing implementation
license: MIT
compatibility: opencode
metadata:
  audience: qa, developers, performance
  workflow: review
---

## What I do
Guide load testing strategy, implementation, and analysis:

### Load Test Types
- **Smoke Test** — Minimal load to verify test script works (1-2 VUs)
- **Load Test** — Expected traffic levels to test normal behavior
- **Stress Test** — Above-normal traffic to find breaking points
- **Spike Test** — Sudden traffic surges
- **Soak Test** — Sustained traffic over time to find memory leaks
- **Breakpoint Test** — Incrementally increase load until failure

### Key Metrics
- Response time (p50, p90, p95, p99)
- Throughput (requests per second)
- Error rate
- Concurrent users / virtual users (VUs)
- Resource utilization (CPU, memory, network)
- Saturation point identification

### Test Design
- Realistic user scenarios (not just single endpoint hammering)
- Think time between actions
- Data parameterization (different users, products)
- Test data isolation (no shared state between VUs)
- Warm-up and cool-down periods

### Tools
- **Karate** — Built-in Gatling integration for load testing
- **k6** — Modern load testing tool (JavaScript)
- **Gatling** — Scala-based, high performance
- **JMeter** — Traditional GUI-based load testing
- **artillery** — Node.js load testing

## When to use me
Use this skill when setting up performance baselines, before production releases, or investigating performance issues.

## Load testing workflow
1. Define SLAs (response time, throughput, error rate)
2. Create realistic test scenarios
3. Run smoke test to validate script
4. Run load test at expected traffic
5. Run stress test to find limits
6. Report results with p50/p90/p99
7. Compare against performance budgets
