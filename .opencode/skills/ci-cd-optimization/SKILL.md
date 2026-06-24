---
name: ci-cd-optimization
description: Optimizes CI/CD pipelines for speed, reliability, and security
license: MIT
compatibility: opencode
metadata:
  audience: devops, developers
  workflow: review
---

## What I do
Analyze and optimize continuous integration and deployment pipelines:

### Pipeline Structure
- Job parallelization (matrix builds, dependency graphs)
- Cache strategy (node_modules, Playwright browsers, Maven artifacts)
- Stage separation (lint → test → build → deploy)
- Conditional execution (skip CI for docs-only changes)

### Test Execution in CI
- Test splitting (sharding) across runners
- Retry strategy (flaky test handling)
- Artifact management (reports, screenshots, logs)
- Timeout configuration
- Environment setup time optimization

### Security in CI
- No secrets in workflow files (use GitHub Secrets)
- Limited permissions on CI tokens
- Dependency vulnerability scanning in pipeline
- Container image scanning
- Branch protection rules

### Deployment
- Staged rollouts (canary, blue-green)
- Rollback strategy
- Database migration safety
- Environment parity (dev/staging/prod)

## When to use me
Use this skill when setting up CI/CD, optimizing slow pipelines, or reviewing deployment safety.

## Key metrics
- Pipeline duration: target < 10min
- Cache hit rate: target > 80%
- Test flakiness rate: target < 1%
- Deployment frequency: target daily+
