---
name: auth-security
description: Reviews authentication and authorization implementation
license: MIT
compatibility: opencode
metadata:
  audience: developers, security
  workflow: review
---

## What I do
Audit authentication and authorization patterns in the codebase:

### Authentication
- Password hashing (bcrypt/argon2, not MD5/SHA1)
- Session management (secure cookies, httpOnly, SameSite)
- Token handling (JWT validation, expiry, refresh flow)
- MFA implementation (TOTP, SMS, recovery codes)
- Password reset flow (rate-limited, time-limited tokens)
- Account lockout after failed attempts

### Authorization
- Role-based access control (RBAC) properly enforced
- No privilege escalation vectors
- API endpoints check authorization, not just authentication
- IDOR (Insecure Direct Object Reference) prevention
- Principle of least privilege applied

### Test Patterns
- Test unauthenticated access returns 401
- Test unauthorized access returns 403
- Test token expiry handling
- Test rate limiting on auth endpoints
- Test password policies are enforced
- Test session invalidation on logout

## When to use me
Use this skill when implementing or reviewing auth systems, before deploying auth changes, or during security audits.

## Key checks
1. Passwords never stored in plaintext
2. Tokens never logged or exposed in URLs
3. Session cookies have `httpOnly`, `secure`, `SameSite=Strict`
4. JWT secrets are strong and rotated
5. Rate limiting on login/register/password-reset
