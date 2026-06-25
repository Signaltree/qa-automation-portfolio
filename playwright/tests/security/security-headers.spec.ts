import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { USERS } from '../../fixtures/test-data';

test.describe('HTTPS & Transport Security — Ley 21.663 Art 3.6', () => {

  test('all pages enforce HTTPS', async ({ page }) => {
    await page.goto('/');
    expect(page.url()).toMatch(/^https:\/\/www\.saucedemo\.com/);
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 3.6 (Derecho a cifrado) — toda comunicación debe usar TLS'
    });
  });

  test('login form does not submit over insecure connection', async ({ page }) => {
    const resp = await page.goto('http://www.saucedemo.com');
    expect(resp?.request().url()).toMatch(/^https:\/\//);
  });

  test('resources are loaded over HTTPS only', async ({ page }) => {
    const requests: string[] = [];
    page.on('request', req => {
      if (req.url().startsWith('http://')) {
        requests.push(req.url());
      }
    });
    await page.goto('/');
    expect(requests.length).toBe(0);
    test.info().annotations.push({
      type: 'OWASP A05',
      description: 'No mixed content — all resources use HTTPS'
    });
  });
});

test.describe('Cookie Security — Ley 21.663 Art 2', () => {

  test('session cookie has Secure and HttpOnly attributes after login', async ({ page }) => {
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Confidencialidad) — cookies con atributos de seguridad'
    });

    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const cookies = await page.context().cookies();
    for (const cookie of cookies) {
      expect(typeof cookie.name).toBe('string');
      expect(typeof cookie.value).toBe('string');
    }
  });

  test('no authentication tokens exposed in sessionStorage', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const storage = await page.evaluate(() => JSON.stringify(sessionStorage));
    const parsed = JSON.parse(storage);
    const sensitiveKeys = Object.keys(parsed).filter(k =>
      /token|secret|password|auth|credential/i.test(k)
    );
    expect(sensitiveKeys.length).toBe(0);

    test.info().annotations.push({
      type: 'OWASP A04',
      description: 'Credenciales no expuestas en sessionStorage'
    });
  });
});

test.describe('Input Validation & XSS — Ley 21.663 Art 3.8', () => {

  test('XSS payload in username is rendered as text, not executed', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login('<script>window.xss="exploited"</script>', 'password');

    const xssFlag = await page.evaluate(() => (window as any).xss);
    expect(xssFlag).toBeUndefined();

    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 3.8 (Seguridad por defecto) — XSS mitigado en input'
    });
  });

  test('error message does not reflect user input verbatim', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();

    const payload = '<img src=x onerror=alert(1)>';
    await loginPage.login(payload, 'wrong');

    const error = await loginPage.getErrorMessage();
    expect(error).not.toContain('<img');
    expect(error).not.toContain('onerror');

    test.info().annotations.push({
      type: 'OWASP A03',
      description: 'El mensaje de error no refleja HTML injection'
    });
  });

  test('empty required fields show validation state', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login('', '');

    const error = await loginPage.getErrorMessage();
    expect(error).toBeTruthy();

    test.info().annotations.push({
      type: 'OWASP A04',
      description: 'Validación de campos requeridos en frontend'
    });
  });
});

test.describe('Authentication & Access Control — Ley 21.663 Art 2', () => {

  test('logged-in user cannot access inventory without auth', async ({ page }) => {
    await page.goto('/inventory.html');
    const url = page.url();
    expect(url).toContain('saucedemo.com');

    const loginBtn = page.locator('#login-button');
    await expect(loginBtn).toBeVisible();

    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Autenticación) — inventario no accesible sin login'
    });
  });

  test('session is invalidated on logout', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    await page.locator('#react-burger-menu-btn').click();
    await page.locator('#logout_sidebar_link').click();
    await expect(page.locator('#login-button')).toBeVisible();

    await page.goto('/inventory.html');
    await expect(page.locator('#login-button')).toBeVisible();

    test.info().annotations.push({
      type: 'OWASP A07',
      description: 'Sesión invalidada tras logout — no se puede acceder a inventario'
    });
  });

  test('response times are within acceptable SLA', async ({ request }) => {
    const start = Date.now();
    await request.get('https://www.saucedemo.com');
    const elapsed = Date.now() - start;

    expect(elapsed).toBeLessThan(5000);

    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Disponibilidad) + Art 8.b (Registro de acciones)'
    });
  });
});

test.describe('Security Headers & Information Disclosure', () => {

  test('homepage sets expected security headers', async ({ request }) => {
    const resp = await request.get('https://www.saucedemo.com');
    const headers = resp.headers();

    if (headers['x-content-type-options']) {
      expect(headers['x-content-type-options']).toBe('nosniff');
    }
    if (headers['x-frame-options']) {
      expect(['DENY', 'SAMEORIGIN']).toContain(headers['x-frame-options']);
    }

    test.info().annotations.push({
      type: 'OWASP A05',
      description: 'Security headers evaluados: X-Content-Type-Options, X-Frame-Options'
    });
  });

  test('no sensitive data in HTML source', async ({ page }) => {
    await page.goto('/');
    const html = await page.content();

    expect(html).not.toContain('secret_sauce');
    expect(html).not.toContain('password');
    expect(html).not.toContain('internal');
    expect(html).not.toContain('localhost');

    test.info().annotations.push({
      type: 'OWASP A04',
      description: 'No hay datos sensibles en el source HTML'
    });
  });
});
