---
name: logging-monitoring
description: Reviews logging, observability, and monitoring implementation
license: MIT
compatibility: opencode
metadata:
  audience: developers, devops
  workflow: review
---

## What I do
Audit logging and observability practices in the codebase:

### Logging Standards
- Structured logging (JSON format, not plain text)
- Log levels used correctly (error, warn, info, debug)
- No sensitive data in logs (passwords, tokens, PII)
- Request IDs for traceability across services
- Sufficient context in log entries (user ID, action, duration)
- Log rotation and retention policies

### Monitoring
- Health check endpoints (`/health`, `/ready`)
- Metrics collection (request rate, error rate, latency)
- Alerting thresholds defined
- Dashboard configuration (if applicable)
- SLA/SLO definitions

### Test-Specific Logging
- Test execution logs include useful debug info
- Screenshots/videos on failure in UI tests
- Network trace capture for API test failures
- Test retry logging

## When to use me
Use this skill when setting up monitoring, reviewing logging implementations, or debugging production issues.

## Logging checklist
- [ ] No `console.log` in production code (use proper logger)
- [ ] Logs are structured/searchable
- [ ] No secrets in logs
- [ ] Health endpoints exist and are tested
- [ ] Error rates are monitored and alerted
