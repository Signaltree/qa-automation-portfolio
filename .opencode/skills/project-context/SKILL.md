---
name: project-context
description: Maintains a CONTEXT.md file that preserves project state across sessions, saving tokens by avoiding re-discovery
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: context
---

## What I do
Manage a persistent `CONTEXT.md` file in the project root that captures everything a future AI session needs to resume work efficiently:

### Why this saves tokens
Instead of re-reading every file, re-discovering architecture, and re-learning decisions each session, `CONTEXT.md` provides a dense summary. On average, this saves **15-30% of context window** on resume sessions.

### What CONTEXT.md contains
```
## Current State
- Frameworks installed and configured
- What the last session worked on
- Completion status of tasks

## Architecture
- Project structure overview
- Design patterns in use (POM, data-driven, etc.)
- Key configuration choices

## Decisions & Rationale
- Why specific approaches were chosen
- Trade-offs considered
- Abandoned alternatives

## Gotchas
- Known issues and workarounds
- Environment quirks
- Flaky test patterns

## Next Steps
- Planned work
- Dependencies for upcoming tasks
- Priority items
```

### When to update CONTEXT.md
1. After completing a significant task
2. When making architecture decisions
3. When discovering workarounds or gotchas
4. Before ending a session (if significant progress was made)
5. When the `/learn` command is used

### When to read CONTEXT.md
1. **Every session start** — Always read CONTEXT.md first before any other file
2. Before making changes that might conflict with known decisions
3. When resuming interrupted work

### Rules
- If `CONTEXT.md` doesn't exist at session start, create it with basic project info
- Keep entries concise — bullet points preferred over paragraphs
- Preserve ALL existing entries when updating; only add or mark items as `[DONE]`
- Never delete information unless it's explicitly superseded
