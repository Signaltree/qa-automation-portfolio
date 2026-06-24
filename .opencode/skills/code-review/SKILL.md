---
name: code-review
description: Performs structured code review for quality, maintainability, and correctness
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: review
---

## What I do
Execute a thorough code review across these dimensions:

### Correctness
- Logic errors and off-by-one bugs
- Race conditions and async issues
- Error handling completeness
- Null/undefined safety
- Edge case coverage

### Maintainability
- Duplicate code (DRY principle)
- Single Responsibility Principle adherence
- Function/class length appropriateness
- Comment quality (why, not what)
- Naming clarity

### Performance
- Unnecessary computations in loops
- Large bundle imports
- Missing memoization or caching
- N+1 query patterns
- Memory leaks (event listeners, timers)

### Testing
- Test covers the change
- Meaningful assertions, not just status checks
- Edge cases covered
- No test pollution (shared mutable state)
- Test descriptions are clear

## When to use me
Use this skill when reviewing PRs, before merging feature branches, or during sprint retrospectives.

## Review format
```
## File: path/to/file.ts (lines X-Y)
### [ISSUE_TYPE] Brief description
**Why:** Explanation of the problem
**Recommendation:** Suggested fix
```
