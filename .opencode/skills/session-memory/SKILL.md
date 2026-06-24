---
name: session-memory
description: Tracks in-session decisions, pending tasks, and progress to maintain continuity
license: MIT
compatibility: opencode
metadata:
  audience: developers
  workflow: context
---

## What I do
Maintain lightweight session memory within a single OpenCode session to avoid repeating context and decisions:

### In-Session State Tracking
Track these without explicit files (via conversation):
- **Active files** — Which files are being worked on
- **Pending decisions** — Choices deferred for later
- **Current task** — What's being built/fixed right now
- **Blockers** — Issues preventing progress
- **Completed subtasks** — What's been done this session

### When to checkpoint
Every time a meaningful milestone is reached, summarize:
1. What was accomplished
2. What changed (files modified, decisions made)
3. What's next

### Handoff Protocol
When a task spans multiple responses:
1. At the end of each response, include a brief status: `[CONTINUE] Status: <what was done> / <what's next> / <any blockers>`
2. At the start of the next interaction, scan for the previous `[CONTINUE]` marker

### Memory Budget Rules
- Don't repeat information already in `CONTEXT.md` or `AGENTS.md`
- Reference file paths instead of quoting file contents
- Use relative paths for files in the project
- Keep in-session summaries under 500 characters each
- If asked about something outside session memory, check `CONTEXT.md` first
