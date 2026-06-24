---
name: secure-config
description: Reviews security configuration (CORS, CSP, TLS, headers, auth)
license: MIT
compatibility: opencode
metadata:
  audience: developers, devops
  workflow: review
---

## What I do
Audit security-related configuration files and settings:

### HTTP Security Headers
- `Content-Security-Policy` configured and not too permissive
- `X-Content-Type-Options: nosniff`
- `X-Frame-Options: DENY` or `SAMEORIGIN`
- `Strict-Transport-Security` with adequate `max-age`
- `X-XSS-Protection` (where supported)

### CORS Configuration
- Origin whitelist is restrictive, not `*` for production
- Credentials flag only set when needed
- Allowed methods are minimal

### TLS/SSL
- Minimum TLS 1.2 enforced
- No weak cipher suites
- Certificates managed properly

### Authentication Config
- Session timeout configured
- Rate limiting in place
- Password policies (min length, complexity)
- MFA where appropriate

### Infrastructure
- Docker/container security (non-root user, no privileged mode)
- Network segmentation (firewall rules, VPC)
- Secrets management (vault, env vars, not files)

## When to use me
Use this skill when setting up a new project, reviewing infrastructure config, or before deployment.

## Checklist
- [ ] CSP header present and restrictive enough
- [ ] CORS not using wildcard origins in production
- [ ] TLS 1.2+ only configured
- [ ] Debug/admin endpoints not exposed publicly
- [ ] Rate limiting configured on auth endpoints
