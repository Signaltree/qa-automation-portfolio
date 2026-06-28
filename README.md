# QA Automation Portfolio / Portafolio de Automatización QA

[![Landing](https://img.shields.io/badge/Landing-Web-00d4ff)](https://signaltree.github.io/qa-portfolio-landing/)
[![Playwright](https://img.shields.io/badge/Playwright-38%20tests-45ba4b?logo=playwright)](playwright/)
[![Karate](https://img.shields.io/badge/Karate-82%20tests-000?logo=java)](karate/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-18%20tests-8A2BE2?logo=java)](rest-assured/)
[![Cucumber BDD](https://img.shields.io/badge/Cucumber-12%20tests-23D96C?logo=cucumber)](bdd-cucumber/)
[![Appium](https://img.shields.io/badge/Appium-7%20tests-663399?logo=appium)](appium/)
[![Selenium WebDriver](https://img.shields.io/badge/Selenium-22%20tests-43B02A?logo=selenium)](selenium-webdriver/)
[![Flutter](https://img.shields.io/badge/Flutter-7%20tests-02569B?logo=flutter)](flutter_app/)
[![k6](https://img.shields.io/badge/k6-3%20scripts-7D64FF?logo=k6)](performance/k6/)
[![JMeter](https://img.shields.io/badge/JMeter-2%20plans-D22128?logo=apachejmeter)](performance/jmeter/)

> **186 tests automatizados** en 7 frameworks.  
> **186 automated tests** across 7 frameworks.  
> [Ver landing page / View landing page](https://signaltree.github.io/qa-portfolio-landing/)

---

## Frameworks

| Framework | Lenguaje | Tests | Objetivo | Estado |
|-----------|----------|-------|----------|--------|
| **Playwright** | TypeScript | 38 (UI + API + Visual + Accesibilidad + Seguridad) | SauceDemo | ✅ |
| **Karate** | Java | 82 (API + GraphQL + Seguridad + Contrato) | JSONPlaceholder + Countries API | ✅ |
| **REST Assured** | Java | 18 (CRUD + Schema + POJO) | JSONPlaceholder | ✅ |
| **Cucumber BDD** | Java | 12 Gherkin + Selenium | SauceDemo | ✅ |
| **Appium** | Java | 7 (Mobile + Navegación) | Wikipedia Android | ✅ |
| **Selenium WebDriver** | Java | 22 (Waits + Multi-browser + Interacción + Frames + JS + POM) | SauceDemo + the-internet | ✅ |
| **Flutter** | Dart | 7 (integration_test) | App demo (contador + todos) | ✅ |

### Rendimiento
| Herramienta | Tests/Planes | Objetivo |
|-------------|-------------|----------|
| **k6** | 3 scripts (smoke, load, stress) | SauceDemo + JSONPlaceholder |
| **JMeter** | 2 planes | SauceDemo page load + JSONPlaceholder API |

---

## Lo que incluye

### [Playwright](playwright/)
- Page Object Model con 4 clases (Login, Inventory, Cart, Checkout)
- Cross-browser: Chromium, Firefox, WebKit, mobile-Chrome, mobile-Safari
- 6 tipos de usuario desde fixtures centralizados
- Intercepción de red, regresión visual, accesibilidad (aXe-core)
- Seguridad: OWASP Top 10, cabeceras HTTP, manejo de sesión
- CI/CD en GitHub Actions con artefactos

### [Karate](karate/)
- 82 tests: CRUD, validación de esquemas, GraphQL, pruebas negativas
- Seguridad: OWASP Top 10 (XSS, SQLi, JWT, SSRF, XXE, CORS)
- Ley 21.663 (CSIRT): 5 escenarios de contrato
- GraphQL: 7 escenarios contra Countries API pública

### [REST Assured](rest-assured/)
- 18 tests: CRUD, validación JSON Schema, serialización POJO
- Matchers Hamcrest: `everyItem`, `hasItem`, `containsString`
- Misma API que Karate (JSONPlaceholder) para comparación directa

### [Cucumber BDD](bdd-cucumber/)
- 12 escenarios Gherkin: login, inventario, carro, checkout
- Selenium 4 + WebDriverManager
- ThreadLocal WebDriver para ejecución paralela
- Ready para Docker con Selenium Grid

### [Appium](appium/)
- 7 tests mobile: búsqueda, resultados, navegación entre artículos
- Android + UiAutomator2
- Pipeline CI con emulador en GitHub Actions

### [Selenium WebDriver](selenium-webdriver/)
- 22 tests puros de Selenium (sin Cucumber): waits, multi-browser, interacción avanzada, ventanas/frames, JavaScript, POM
- SauceDemo para POM + the-internet para features avanzados
- Chrome, Firefox y Edge headless

### [Flutter](flutter_app/)
- 7 integration tests: renderizado, contador, CRUD de tareas
- App demo con contador + lista de tareas (toggle, delete)
- Build analizado sin issues

### [k6](performance/k6/)
- Smoke test, load test, stress test
- Ejecución via Docker
- Umbrales: duración de petición, tasa de fallos

### [JMeter](performance/jmeter/)
- Plan smoke para SauceDemo (page load + assets)
- Plan API para JSONPlaceholder (GET/POST)
- Reportes HTML generados automáticamente

---

## Stack

| Área | Tecnologías |
|------|-------------|
| **UI Testing** | Playwright, TypeScript, POM |
| **API Testing** | Karate (Cucumber), REST Assured (Hamcrest) |
| **Mobile** | Appium, UiAutomator2, Android |
| **Performance** | k6, JMeter |
| **Security** | OWASP Top 10, aXe-core, Ley 21.663 |
| **BDD** | Cucumber 7, Selenium 4, Gherkin |
| **CI/CD** | GitHub Actions (5 workflows) |
| **Container** | Docker Compose, Selenium Grid |
| **Assistant** | OpenCode con 24 skills |

---

## Inicio rápido

```bash
# Playwright
npm install
npx playwright install chromium
npm test

# Karate
cd karate && mvn test

# REST Assured
cd rest-assured && mvn test

# BDD Cucumber
cd bdd-cucumber && mvn test

# Appium
cd appium && mvn test

# Selenium WebDriver
cd selenium-webdriver && mvn test

# Flutter
cd flutter_app && flutter test

# k6 (via Docker)
npm run test:k6:smoke

# JMeter
npm run test:jmeter
```

---

## Resultados

| Suite | Tests | Estado |
|-------|-------|--------|
| Playwright UI | 12 | ✅ |
| Playwright Security | 7 | ✅ |
| Playwright Visual | 8 | ✅ |
| Playwright API | 8 | ✅ |
| Playwright Accesibilidad | 3 | ✅ |
| Karate API | 82 | ✅ |
| REST Assured | 18 | ✅ |
| Cucumber BDD | 12 | ✅ |
| Appium | 7 | ✅ |
| Selenium WebDriver | 22 | ✅ |
| Flutter | 7 | ✅ |
| k6 | 3 scripts | ✅ |
| JMeter | 2 plans | ✅ |
| **Total** | **186** | ✅ |

---

## Licencia

MIT — ver [LICENSE](LICENSE)

---

[Landing page](https://signaltree.github.io/qa-portfolio-landing/) · [OpenCode](https://opencode.ai)
