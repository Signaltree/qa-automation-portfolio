---
name: dependency-security
description: Audits third-party dependencies for known vulnerabilities
license: MIT
compatibility: opencode
metadata:
  audience: developers, security
  workflow: review
---

## What I do
Analyze the project's dependencies for security risks:

- Check `package.json` / `requirements.txt` / `pom.xml` for outdated packages
- Flag packages with known CVEs (Common Vulnerabilities and Exposures)
- Verify lock files are present and committed (`package-lock.json`, `yarn.lock`)
- Check for abandoned or unmaintained packages
- Identify packages with excessive permissions or telemetry
- Verify dependency update frequency and patch strategy
- Check for transitive dependency risks

## When to use me
Use this skill when adding new dependencies, before major releases, or as part of regular security maintenance.

## Recommended tools
```bash
npm audit                  # Basic vulnerability audit
npm audit --audit-level=high  # Only high/critical
npx snyk test              # Snyk vulnerability scanner
npx renovate               # Automated dependency updates
```

## Rules
1. Lock files must be committed to repo
2. Pin major versions, allow patch updates with `^` or `~`
3. No deprecated packages
4. No packages with known critical/high CVEs without documented exception
5. Audit regularly (at least monthly)
