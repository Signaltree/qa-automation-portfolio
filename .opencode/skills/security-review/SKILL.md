---
name: security-review
description: Audits for common security vulnerabilities in test code and configurations
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: review
---

## What I do
- Check for exposed secrets, API keys, or credentials in the codebase
- Verify `.gitignore` excludes sensitive files (.env, credentials, etc.)
- Validate that environment variables are used for sensitive data
- Check that the `.env.example` file doesn't contain real secrets
- Ensure CI/CD config doesn't expose tokens or keys in logs
- Review that test data doesn't include real user credentials
- Verify that network interception tests don't introduce security holes
- Check that visual snapshots don't capture sensitive information

## When to use me
Use this skill before pushing to a public repository, when adding CI/CD secrets, or when reviewing any code that handles authentication or user data.

## Key checks
1. No credentials in source code
2. `.env` files are gitignored
3. `.env.example` has placeholder values only
4. CI secrets are managed through GitHub Secrets, not in workflow files
5. No internal URLs or service endpoints exposed
