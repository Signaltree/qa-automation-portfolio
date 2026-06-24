---
description: Learn project context — updates CONTEXT.md to save tokens across sessions
---

## Command: /learn

Your job is to scan the project and update/create `CONTEXT.md` with everything a future AI session needs to resume work without re-discovering the project.

### What to capture in CONTEXT.md

1. **Current State**
   - What frameworks/tools exist (Playwright, Karate, Selenium, etc.)
   - What the last session was working on
   - What's complete vs what's pending

2. **Architecture Decisions**
   - Why specific patterns were chosen (POM, data-driven, etc.)
   - Important design decisions made during development
   - Configuration choices (browser matrix, CI strategy)

3. **Gotchas & Lessons**
   - Issues encountered and how they were resolved
   - SauceDemo-specific quirks (SPA, no POST, JS bundle data)
   - Test flakiness patterns and mitigations

4. **Next Steps**
   - What the roadmap looks like
   - What the user planned to work on next
   - Dependencies or prerequisites for upcoming work

### Rules
- Read existing `CONTEXT.md` first (if it exists) — preserve and update it
- Read `AGENTS.md` for project structure reference
- Scan key directories (`playwright/`, `.opencode/skills/`, etc.) to understand current state
- Be concise — this file is read at the start of every session to save tokens
- Group information under clear headings: `## Current State`, `## Architecture`, `## Next Steps`, etc.
- If CONTEXT.md already exists, merge new info with existing content (don't delete useful context)
