---
name: error-handling
description: Reviews error handling patterns, resilience, and fault tolerance
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: review
---

## What I do
Audit how the codebase handles errors and exceptional conditions:

### Error Handling Patterns
- Try/catch blocks cover expected failure modes
- Async errors are caught (unhandled promise rejections)
- Error types are specific (not generic `Error`)
- Errors are logged with sufficient context
- User-facing errors don't leak internals (stack traces)

### Resilience
- Retry logic with exponential backoff for network calls
- Circuit breaker patterns for external dependencies
- Graceful degradation when services are unavailable
- Timeout handling for all external calls
- Fallback values or defaults when data is missing

### Testing Error Scenarios
- Network failure simulation (offline mode)
- API error responses (4xx, 5xx)
- Timeout scenarios
- Invalid/malformed input handling
- Concurrent access conflicts
- Resource exhaustion (memory, disk, connections)

## When to use me
Use this skill when building error-sensitive features, reviewing API integrations, or before resilience testing.

## Error handling maturity levels
1. **Basic** — Try/catch around risky operations
2. **Standard** — Specific error types, logging, user messages
3. **Advanced** — Retry with backoff, circuit breakers, fallbacks
4. **Resilient** — Chaos engineering, fault injection testing, self-healing
