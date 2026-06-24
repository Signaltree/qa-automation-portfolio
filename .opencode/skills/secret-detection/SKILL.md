---
name: secret-detection
description: Detects hardcoded secrets, API keys, tokens, and credentials
license: MIT
compatibility: opencode
metadata:
  audience: developers, security
  workflow: review
---

## What I do
Scan the codebase for accidentally committed secrets and credentials:

- **API keys** — Patterns like `sk-...`, `pk-...`, `api_key`, `apikey`
- **Tokens** — JWT, OAuth, bearer tokens, session secrets
- **Passwords** — Plaintext passwords in configs, test data, comments
- **Connection strings** — Database URLs with embedded credentials
- **Private keys** — RSA, DSA, EC private key blocks (`-----BEGIN`)
- **Cloud credentials** — AWS keys, GCP service accounts, Azure secrets
- **Environment exposure** — `.env` files not in `.gitignore`

## When to use me
Use this skill before pushing to public repositories, when onboarding to a new project, or after merging PRs.

## Remediation rules
1. Never commit secrets — use environment variables or secret managers
2. If a secret is committed, rotate it immediately
3. Use `.env.example` with placeholder values only
4. Add secret patterns to `.gitignore` or use `.gitattributes`

## Patterns I flag
```regex
(?i)(api[_-]?key|secret|password|token|auth|credential|private[_-]?key)
\b(?:sk-|pk-|ghp_|gho_|ghu_|ghs_|ghr_)[A-Za-z0-9_-]{20,}\b
-----BEGIN (RSA|DSA|EC|OPENSSH|PGP) PRIVATE KEY-----
```
