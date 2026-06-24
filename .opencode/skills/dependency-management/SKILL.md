---
name: dependency-management
description: Manages project dependencies for health, versioning, and compatibility
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: review
---

## What I do
Analyze and optimize how the project manages its dependencies:

### Versioning Strategy
- Semantic versioning adherence
- Major version pinning vs. floating ranges
- Lock file integrity and commitment
- Peer dependency compatibility

### Dependency Health
- Outdated packages identification
- Deprecated package detection
- Unused dependency removal
- Duplicate dependencies (multiple versions of same package)
- Bundle size contribution analysis

### Tool-Specific Checks
- **Node**: Check package.json, package-lock.json, .nvmrc
- **Java/Maven**: Check pom.xml for scope correctness
- **Python**: Check requirements.txt, Pipfile.lock
- **Docker**: Multi-stage builds, base image versions

## When to use me
Use this skill when adding new dependencies, before major upgrades, or during regular maintenance sprints.

## Commands I recommend
```bash
# Node.js
npm outdated           # List outdated packages
npm ls --depth=0       # List top-level dependencies
npx depcheck           # Find unused dependencies
npx npm-check-updates  # Interactive upgrade tool
npx size-limit         # Bundle size analysis
```
