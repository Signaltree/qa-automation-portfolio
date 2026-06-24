---
name: owasp-top-10
description: Checks code against OWASP Top 10 vulnerabilities (2021)
license: MIT
compatibility: opencode
metadata:
  audience: developers, security
  workflow: review
---

## What I do
Audit the codebase against the OWASP Top 10 web application security risks:

1. **A01 Broken Access Control** — Verify role checks, avoid IDOR patterns
2. **A02 Cryptographic Failures** — Check for weak TLS, plaintext secrets
3. **A03 Injection** — Detect SQL/NoSQL/command injection vectors
4. **A04 Insecure Design** — Review architecture for security gaps
5. **A05 Security Misconfiguration** — Check CORS, CSP, headers, debug mode
6. **A06 Vulnerable Components** — Flag outdated dependencies
7. **A07 Auth Failures** — Review authentication logic
8. **A08 Data Integrity Failures** — Check for unsafe deserialization
9. **A09 Logging Failures** — Ensure security-relevant events are logged
10. **A10 SSRF** — Review external URL handling

## When to use me
Use this skill for any web project before release, during security audits, or when adding features that handle user data or authentication.

## Output format
For each finding, report: `[OWASP-A0X] File:line — Description`
