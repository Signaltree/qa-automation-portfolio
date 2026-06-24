import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { USERS } from '../../fixtures/test-data';

test.describe('Network Interception and Mocking', () => {
  test('should verify URL changes after login', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    await expect(page).toHaveURL(/inventory\.html/);
  });

  test('should block CSS to test unstyled page', async ({ page }) => {
    await page.route('**/*.css', async route => {
      await route.abort();
    });

    const loginPage = new LoginPage(page);
    await loginPage.goto();

    await expect(loginPage.usernameInput).toBeVisible();
    const hasMinimalStyles = await loginPage.usernameInput.evaluate(el => {
      const styles = window.getComputedStyle(el);
      return styles.visibility !== 'hidden';
    });
    expect(hasMinimalStyles).toBe(true);
  });

  test('should intercept JavaScript bundle and modify inventory data', async ({ page }) => {
    await page.route('**/static/js/main.*.js', async route => {
      const response = await route.fetch();
      const body = await response.text();
      const modified = body.replace(
        'Sauce Labs Backpack',
        'Sauce Labs Backpack [MODIFIED]'
      );
      await route.fulfill({
        body: modified,
        contentType: 'application/javascript',
      });
    });

    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const item = page.locator('.inventory_item_name', {
      hasText: 'Sauce Labs Backpack [MODIFIED]',
    });
    await expect(item).toBeVisible({ timeout: 10000 });
  });

  test('should block image loading for performance testing', async ({ page }) => {
    await page.route('**/*.{png,jpg,jpeg,gif,svg}', async route => {
      await route.abort();
    });

    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const images = page.locator('.inventory_item img');
    const count = await images.count();
    for (let i = 0; i < count; i++) {
      const img = images.nth(i);
      const isBroken = await img.evaluate((el: HTMLImageElement) => {
        return el.naturalWidth === 0 || el.naturalHeight === 0;
      });
      expect(isBroken).toBe(true);
    }
  });

  test('should measure network request durations', async ({ page }) => {
    const timings: { url: string; ms: number }[] = [];

    await page.route('**/*', async route => {
      const start = Date.now();
      await route.continue();
      timings.push({ url: route.request().url(), ms: Date.now() - start });
    });

    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    expect(timings.length).toBeGreaterThan(0);
    const slow = timings.filter(t => t.ms > 1000);
    console.log(`Total requests: ${timings.length}, Slow (>1s): ${slow.length}`);
  });
});
