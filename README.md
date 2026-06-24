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

## OpenCode Skills

This project uses **OpenCode Skills** — reusable instructions that guide the AI agent during development. Skills are loaded on-demand when the agent recognizes a matching task.

### Available Skills

| Skill | Description |
|-------|-------------|
| `architecture-review` | Reviews project structure, layering, and design patterns |
| `code-quality` | Ensures clean code, follows TypeScript/Playwright best practices |
| `security-review` | Audits for common vulnerabilities (XSS, injection, auth flaws) |
| `testing-standards` | Enforces testing patterns, Page Object Model, and coverage |

### How Skills Work

Skills live in `.opencode/skills/<name>/SKILL.md` files. When OpenCode detects a relevant task, it automatically loads the skill's instructions. For example, when asked to write tests, the `testing-standards` skill activates and enforces consistent patterns.

This is similar to the "Claude Council" concept from Claude Code — a set of specialized skills that collaborate to ensure project quality across architecture, code, security, and testing.

### Creating Your Own Skills

```bash
# Create a skill directory
mkdir -p .opencode/skills/my-skill

# Create the SKILL.md file
cat > .opencode/skills/my-skill/SKILL.md << 'EOF'
---
name: my-skill
description: What this skill does
---
Your instructions here...
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

---

*Built with [OpenCode](https://opencode.ai) — the open source AI coding agent.*
