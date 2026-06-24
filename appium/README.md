# Appium — Mobile Testing

Proyecto de automatización mobile con **Appium 9** + **TestNG** + **Allure Reports**, probando la app de **Wikipedia** en Android.

## Stack

- **Appium 9.3.0** (Java Client)
- **TestNG 7.10.2**
- **Allure 2.29.0**
- **Page Object Model**
- **Android UiAutomator2**

## Tests

| Test | Descripción |
|------|-------------|
| `testSearchReturnsResults` | Busca "Selenium" y verifica resultados |
| `testSearchResultTitle` | Busca "Appium" y verifica el título del primer resultado |
| `testSearchResultHasDescription` | Busca "Testing" y verifica que tenga descripción |
| `testSearchInputAccessible` | Verifica que el campo de búsqueda sea interactuable |

## Requisitos

- Java 21+
- Appium Server 2.x (`npm install -g appium`)
- Android SDK (emulador o dispositivo físico)
- Appium Inspector (opcional, para inspeccionar elementos)

## Ejecución

### 1. Iniciar Appium Server

```bash
appium
```

### 2. Iniciar emulador o conectar dispositivo

```bash
emulator -avd Pixel_9_API_35  # o conectar dispositivo USB
```

### 3. Ejecutar tests

```bash
cd appium
mvn clean test
```

### Con app APK personalizado

```bash
mvn clean test -DappPath=/ruta/a/wikipedia.apk
```

### Variables de entorno

| Variable | Default | Descripción |
|----------|---------|-------------|
| `APPIUM_URL` | `http://127.0.0.1:4723` | URL del server Appium |
| `APPIUM_DEVICE_NAME` | `Android Emulator` | Nombre del dispositivo |
| `APPIUM_PLATFORM_VERSION` | `15.0` | Versión de Android |
| `APPIUM_APP_PATH` | (usa package/activity) | Ruta al APK |

## Reportes Allure

```bash
mvn allure:serve
```

## Estructura

```
appium/
├── pom.xml
├── src/test/
│   ├── java/com/portfolio/appium/
│   │   ├── config/AppiumConfig.java
│   │   ├── pages/
│   │   │   ├── BasePage.java
│   │   │   ├── HomePage.java
│   │   │   └── SearchPage.java
│   │   └── tests/
│   │       ├── BaseTest.java
│   │       └── WikipediaSearchTest.java
│   └── resources/testng.xml
└── README.md
```
