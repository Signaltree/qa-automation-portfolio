# QA Automation Portfolio / Portafolio de Automatización QA

[English](#english) | [Español](#español)

---

## English

**Multi-framework QA automation portfolio** built with **Playwright**, TypeScript, Page Object Model, API interception, visual regression, and CI/CD integration.

This project was developed with **OpenCode** — an open source AI coding agent — using a custom **Project Quality skillset** that includes architecture review, code quality, security auditing, and testing standards skills.

### Tech Stack

| Area | Technology |
|------|-----------|
| **Framework** | Playwright |
| **Language** | TypeScript |
| **Pattern** | Page Object Model |
| **Reporting** | Playwright HTML Reporter |
| **CI/CD** | GitHub Actions |
| **Target App** | SauceDemo (Swag Labs) |
| **AI Assistant** | OpenCode |

### Project Structure

```
playwright/
├── tests/
│   ├── ui/              # UI functional tests (login, inventory, cart)
│   ├── api/             # Network interception & mocking
│   └── visual/          # Visual regression tests
├── pages/               # Page Object Models
├── fixtures/            # Test data
├── utils/               # Helper utilities
.opencode/skills/        # OpenCode skills (see skills section below)
```

### Quick Start

```bash
npm install
npx playwright install chromium
npm test
```

### Running Tests

```bash
npm test                 # All tests (Chromium)
npm run test:firefox     # Firefox
npm run test:webkit      # Safari
npm run test:mobile      # Mobile viewports
npm run test:api         # API interception tests
npm run test:visual      # Visual regression tests
npm run report           # Open HTML report
```

### Key Features

- **Page Object Model** — Maintainable, reusable abstractions
- **Cross-browser** — Chromium, Firefox, WebKit
- **Mobile testing** — Responsive design validation
- **Network interception** — JS bundle modification, CSS blocking, request timing
- **Visual regression** — Screenshot comparison with diff thresholds
- **Data-driven** — 6 user types with varied behavior
- **CI/CD** — GitHub Actions with matrix builds
- **Reporting** — HTML reports with traces, screenshots, video

---

## Español

**Portafolio de automatización QA multi-framework** construido con **Playwright**, TypeScript, Page Object Model, intercepción de API, regresión visual e integración CI/CD.

Este proyecto fue desarrollado con **OpenCode** — un agente de codificación AI de código abierto — utilizando un conjunto personalizado de **skills de calidad de proyecto** que incluye revisión de arquitectura, calidad de código, auditoría de seguridad y estándares de pruebas.

### Stack Tecnológico

| Área | Tecnología |
|------|-----------|
| **Framework** | Playwright |
| **Lenguaje** | TypeScript |
| **Patrón** | Page Object Model |
| **Reportes** | Playwright HTML Reporter |
| **CI/CD** | GitHub Actions |
| **App Objetivo** | SauceDemo (Swag Labs) |
| **Asistente AI** | OpenCode |

### Estructura del Proyecto

```
playwright/
├── tests/
│   ├── ui/              # Pruebas funcionales UI (login, inventario, carrito)
│   ├── api/             # Intercepción de red y mocking
│   └── visual/          # Pruebas de regresión visual
├── pages/               # Page Object Models
├── fixtures/            # Datos de prueba
├── utils/               # Utilidades
.opencode/skills/        # Skills de OpenCode
```

### Inicio Rápido

```bash
npm install
npx playwright install chromium
npm test
```

### Ejecutar Pruebas

```bash
npm test                 # Todas las pruebas (Chromium)
npm run test:firefox     # Firefox
npm run test:webkit      # Safari
npm run test:mobile      # Viewports móviles
npm run test:api         # Pruebas de intercepción API
npm run test:visual      # Pruebas de regresión visual
npm run report           # Abrir reporte HTML
```

### Características Clave

- **Page Object Model** — Abstracciones reutilizables y mantenibles
- **Multi-navegador** — Chromium, Firefox, WebKit
- **Pruebas móviles** — Validación de diseño responsivo
- **Intercepción de red** — Modificación de JS, bloqueo de CSS, medición de tiempos
- **Regresión visual** — Comparación de capturas con umbrales de diferencia
- **Data-driven** — 6 tipos de usuario con comportamiento variado
- **CI/CD** — GitHub Actions con builds en matriz
- **Reportes** — Reportes HTML con trazas, capturas, video

---

## OpenCode Skills Ecosystem

This project uses **OpenCode Skills** — reusable instructions that guide the AI agent during development. Skills are loaded on-demand when OpenCode recognizes a matching task.

The ecosystem is organized into **5 categories** with **17 specialized skills** that collaborate to ensure project quality at every level. This is similar to the "Claude Council" concept — a council of specialized skills working together.

### Category 1: Security (Primary Focus)

These skills form a comprehensive security audit layer. They can be invoked individually or orchestrated by `security-review`.

| Skill | Description |
|-------|-------------|
| `security-review` | **Orchestrator** — Full security audit across all layers |
| `owasp-top-10` | Checks against OWASP Top 10 (2021) vulnerabilities |
| `secret-detection` | Detects hardcoded API keys, tokens, passwords, credentials |
| `secure-config` | Audits CORS, CSP, TLS, headers, authentication config |
| `dependency-security` | Scans dependencies for known CVEs and vulnerabilities |
| `auth-security` | Reviews auth flows, RBAC, JWT, session handling |

### Category 2: Architecture & Design

| Skill | Description |
|-------|-------------|
| `architecture-review` | Reviews project structure, layering, design patterns |
| `design-patterns` | Validates pattern usage (POM, Factory, Builder, etc.) |

### Category 3: Code Quality & Review

| Skill | Description |
|-------|-------------|
| `code-quality` | Enforces TypeScript strict mode, naming, best practices |
| `code-review` | Structured code review (correctness, maintainability, perf) |
| `documentation` | Ensures README, API docs, and operational docs quality |
| `dependency-management` | Manages versions, lock files, unused deps |

### Category 4: Project Optimization

| Skill | Description |
|-------|-------------|
| `project-optimization` | Holistic optimization (build, runtime, DX, code health) |
| `performance-review` | Test/application performance, flakiness, budgets |
| `error-handling` | Audits error handling, resilience, retry patterns |
| `logging-monitoring` | Reviews logging, observability, health endpoints |
| `ci-cd-optimization` | Optimizes pipeline speed, reliability, security |

### Category 5: Testing Standards

| Skill | Description |
|-------|-------------|
| `testing-standards` | Enforces test patterns, naming, edge cases |
| `api-testing` | Guides API contract, integration, negative testing |
| `load-testing` | Guides load, stress, spike, soak testing |

### How Skills Work

```
.opencode/skills/<skill-name>/SKILL.md
```

Each skill is a Markdown file with YAML frontmatter. When you're working on a relevant task, OpenCode discovers and loads the matching skill automatically. You can see available skills in the session — they appear in the skill tool's description.

**Example workflow with OpenCode:**
1. You ask: "Review the project structure"
2. OpenCode detects `architecture-review` skill → loads it
3. The skill guides OpenCode to check layering, POM, config, CI/CD
4. You get architecture recommendations

**Orchestrated audit:**
1. You ask: "Run a full security audit"
2. `security-review` (orchestrator) loads → delegates to:
   - `owasp-top-10` → vulnerability checks
   - `secret-detection` → credential scan
   - `dependency-security` → CVE audit
   - `secure-config` → configuration review
   - `auth-security` → auth flow review

### Creating New Skills

```bash
# Create the skill
mkdir -p .opencode/skills/your-skill-name

# Create SKILL.md (name must match directory)
cat > .opencode/skills/your-skill-name/SKILL.md << 'EOF'
---
name: your-skill-name
description: Brief description for OpenCode to recognize when to load this
---
## What I do
Describe the skill's purpose and behavior.

## When to use me
Describe when this skill should be activated.
EOF
```

---

## CI/CD Pipeline

The GitHub Actions workflow runs tests across Chromium, Firefox, and WebKit on every push. Test artifacts (reports, screenshots, videos) are uploaded for debugging.

---

## CI/CD Pipeline / Pipeline CI/CD

| EN | ES |
|----|-----|
| The GitHub Actions workflow runs tests across Chromium, Firefox, and WebKit on every push. Artifacts are uploaded for debugging. | El workflow de GitHub Actions ejecuta pruebas en Chromium, Firefox y WebKit en cada push. Los artefactos se suben para depuración. |

## Skills Ecosystem / Ecosistema de Skills

| EN | ES |
|----|-----|
| The project includes **17 OpenCode Skills** organized in 5 categories to ensure code quality, security, architecture, testing, and optimization. Skills are loaded automatically by OpenCode when relevant tasks are detected. | El proyecto incluye **17 Skills de OpenCode** organizadas en 5 categorías para asegurar calidad de código, seguridad, arquitectura, pruebas y optimización. Las skills se cargan automáticamente cuando OpenCode detecta tareas relevantes. |
| **Security (6 skills):** security-review, owasp-top-10, secret-detection, secure-config, dependency-security, auth-security | **Seguridad (6 skills):** security-review, owasp-top-10, secret-detection, secure-config, dependency-security, auth-security |
| **Architecture (2):** architecture-review, design-patterns | **Arquitectura (2):** architecture-review, design-patterns |
| **Code Quality (4):** code-quality, code-review, documentation, dependency-management | **Calidad de Código (4):** code-quality, code-review, documentation, dependency-management |
| **Optimization (5):** project-optimization, performance-review, error-handling, logging-monitoring, ci-cd-optimization | **Optimización (5):** project-optimization, performance-review, error-handling, logging-monitoring, ci-cd-optimization |
| **Testing (3):** testing-standards, api-testing, load-testing | **Pruebas (3):** testing-standards, api-testing, load-testing |

---

*Built with [OpenCode](https://opencode.ai) — the open source AI coding agent.*
