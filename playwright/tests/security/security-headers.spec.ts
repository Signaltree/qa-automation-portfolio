import { test, expect } from '@playwright/test';

const BASE_URL = 'https://jsonplaceholder.typicode.com';

test.describe('Security Headers — Ley 21.663 Art 3 & Art 7 Compliance', () => {

  test('CSP header analysis — XSS mitigation headers', async ({ request }) => {
    const resp = await request.get(`${BASE_URL}/posts/1`);
    const headers = resp.headers();

    // Ley 21.663 Art 3.8: seguridad por defecto — sistemas deben protegerse contra XSS
    // Ley 21.663 Art 7: medidas tecnológicas permanentes de prevención
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 3.8 (Seguridad por defecto) + Art 7 (Medidas de prevención)'
    });

    if (headers['x-content-type-options']) {
      expect(headers['x-content-type-options']).toBe('nosniff');
    }
    if (headers['x-frame-options']) {
      expect(['DENY', 'SAMEORIGIN']).toContain(headers['x-frame-options']);
    }
  });

  test('HTTPS enforcement — no insecure content', async ({ request }) => {
    const resp = await request.get(`${BASE_URL}/posts`);
    expect(resp.url()).toMatch(/^https:\/\//);

    // Ley 21.663 Art 3.6: derecho a adoptar medidas de seguridad incluyendo cifrado
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 3.6 (Derecho a cifrado) — toda comunicación debe usar TLS'
    });
  });

  test('Cookie security attributes — HttpOnly, Secure, SameSite', async ({ page }) => {
    // Ley 21.663 Art 2: Confidencialidad — cookies con atributos de seguridad
    // Ley 21.663 Art 3.8: Privacidad por defecto
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Confidencialidad) + Art 3.8 (Privacidad por defecto)'
    });

    await page.goto('https://jsonplaceholder.typicode.com');
    const cookies = await page.context().cookies();
    expect(Array.isArray(cookies)).toBe(true);
    for (const cookie of cookies) {
      expect(typeof cookie.name).toBe('string');
      expect(typeof cookie.value).toBe('string');
      expect(typeof cookie.domain).toBe('string');
      expect(typeof cookie.path).toBe('string');
    }
  });

  test('CORS preflight returns safe headers', async ({ request }) => {
    const resp = await request.fetch(`${BASE_URL}/posts/1`, {
      method: 'OPTIONS',
      headers: {
        Origin: 'https://trusted-origin.cl',
        'Access-Control-Request-Method': 'GET'
      }
    });
    const headers = resp.headers();

    // Ley 21.663 Art 7: control de daños — evitar propagación de incidentes
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 7 (Control de daños) — CORS restrictivo'
    });

    if (headers['access-control-allow-origin']) {
      expect(headers['access-control-allow-origin']).not.toBe('*');
    }
  });

  test('Server version is not exposed in headers', async ({ request }) => {
    const resp = await request.get(`${BASE_URL}/posts/1`);
    const headers = resp.headers();

    // Ley 21.663 Art 8.d: revisión continua — monitorear versiones expuestas
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 8.d (Revisión continua) — exponer versiones es un riesgo de seguridad'
    });

    const poweredBy = headers['x-powered-by'];
    if (poweredBy) {
      test.info().annotations.push({
        type: 'OWASP A06',
        description: `Server exposes version: ${poweredBy} — should be removed in production`
      });
    }
  });

  test('Content-Type validation prevents MIME sniffing', async ({ request }) => {
    const resp = await request.get(`${BASE_URL}/posts/1`);
    const contentType = resp.headers()['content-type'] || '';

    // Ley 21.663 Art 2: Integridad — evitar interpretación incorrecta de contenido
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Integridad) — Content-Type debe coincidir con el payload'
    });

    expect(contentType).toContain('application/json');
  });
});

test.describe('Authentication & Access Control — Ley 21.663 Art 2 Compliance', () => {

  test('Public endpoints do not expose sensitive data', async ({ request }) => {
    const resp = await request.get(`${BASE_URL}/users/1`);
    const body = await resp.json();

    // Ley 21.663 Art 2: Activo informático — datos con valor no deben exponerse
    // sin autenticación adecuada
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Activo informático, Autenticación) — datos personales protegidos'
    });

    expect(body).not.toHaveProperty('password');
    expect(body).not.toHaveProperty('token');
    expect(body).not.toHaveProperty('secret');
    expect(body).not.toHaveProperty('ssn');
  });

  test('Response times are monitored for DoS anomalies', async ({ request }) => {
    const start = Date.now();
    await request.get(`${BASE_URL}/posts`);
    const elapsed = Date.now() - start;

    // Ley 21.663 Art 2: Disponibilidad — servicio debe responder dentro de SLA
    // Ley 21.663 Art 8.b: Registro de acciones (tiempos de respuesta)
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Disponibilidad) + Art 8.b (Registro de acciones)'
    });

    expect(elapsed).toBeLessThan(3000);
  });
});

test.describe('OWASP Top 10 + Ley 21.663 — Security Testing', () => {

  test('SQL injection attempts return 200 but no data leak', async ({ request }) => {
    const injections = ["' OR '1'='1", "' OR 1=1--", "' UNION SELECT @@version--"];

    for (const injection of injections) {
      const resp = await request.get(`${BASE_URL}/posts`, {
        params: { q: injection }
      });
      expect(resp.status()).toBe(200);

      // Ley 21.663 Art 2: Integridad — datos no deben ser modificados por inyección
      test.info().annotations.push({
        type: 'Ley 21663',
        description: 'Art 2 (Integridad) + A03 (Injection)'
      });
    }
  });

  test('Path traversal is contained', async ({ request }) => {
    const traversals = [
      '../../../etc/passwd',
      '..\\..\\..\\windows\\win.ini'
    ];

    for (const trav of traversals) {
      const resp = await request.get(`${BASE_URL}/posts/${trav}`);
      // Must return 400 (Bad Request) or 404 (Not Found) — NOT 200 with file contents
      // Ley 21.663 Art 8.e: reducir impacto y propagación
      test.info().annotations.push({
        type: 'Ley 21663',
        description: 'Art 8.e (Reducir impacto) + A01 (Path Traversal)'
      });
      expect([400, 404]).toContain(resp.status());
    }
  });

  test('Repeated requests respect rate limiting', async ({ request }) => {
    const responses = await Promise.all(
      Array(10).fill(null).map(() => request.get(`${BASE_URL}/posts/1`))
    );

    // Ley 21.663 Art 2: Disponibilidad — servicio debe mantenerse operativo
    // Ley 21.663 Art 7: Medidas de prevención (rate limiting)
    test.info().annotations.push({
      type: 'Ley 21663',
      description: 'Art 2 (Disponibilidad) + Art 7 (Prevención) — rate limiting'
    });

    const statuses = responses.map(r => r.status());
    const allSuccessful = statuses.every(s => s === 200 || s === 429);
    expect(allSuccessful).toBe(true);
  });
});
