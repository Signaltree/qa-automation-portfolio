---
name: security-review
description: Orchestrates a full security audit across all project layers
license: MIT
compatibility: opencode
metadata:
  audience: developers, security
  workflow: review
---

## What I do
Coordinate a comprehensive security review by invoking specialized sub-checks:

1. **Source Code Scan** — Look for hardcoded secrets, injection risks, unsafe patterns
2. **Dependency Audit** — Check for vulnerable or outdated packages
3. **Configuration Review** — Audit CORS, CSP, TLS, auth, headers
4. **Authentication & Authorization** — Verify auth flows, RBAC, session handling
5. **Data Protection** — Check encryption, PII handling, data exposure
6. **Infrastructure** — Review Dockerfiles, CI/CD secrets, network config
7. **Test Coverage** — Ensure security scenarios are tested

### Common vulnerability patterns to flag
- SQL/NoSQL injection
- Cross-Site Scripting (XSS)
- Cross-Site Request Forgery (CSRF)
- Server-Side Request Forgery (SSRF)
- Path traversal
- Insecure deserialization
- Mass assignment
- Business logic flaws

## When to use me
Use this skill as the primary security gate before any release. It orchestrates the full audit and delegates to specialized skills (owasp-top-10, secret-detection, dependency-security, auth-security, secure-config) as needed.

## Output
For each finding provide: `[SEVERITY] Category — File:line — Description — Recommendation`
Severities: `CRITICAL`, `HIGH`, `MEDIUM`, `LOW`, `INFO`
