import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { USERS } from '../../fixtures/test-data';

test.describe('Visual Regression Testing', () => {
  test('login page should match snapshot', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();

    await expect(page).toHaveScreenshot('login-page.png', {
      fullPage: true,
      maxDiffPixelRatio: 0.05,
    });
  });

  test('inventory page should match snapshot', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    await expect(page).toHaveScreenshot('inventory-page.png', {
      fullPage: true,
      maxDiffPixelRatio: 0.05,
    });
  });

  test('cart page should match snapshot with items', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    await page.locator('[data-test="add-to-cart-sauce-labs-backpack"]').click();
    await page.locator('[data-test="add-to-cart-sauce-labs-bike-light"]').click();
    await page.locator('.shopping_cart_link').click();

    await expect(page).toHaveScreenshot('cart-page.png', {
      fullPage: true,
      maxDiffPixelRatio: 0.05,
    });
  });

  test.describe('Responsive Design', () => {
    test('inventory page on mobile viewport', async ({ page }) => {
      await page.setViewportSize({ width: 390, height: 844 });

      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login(USERS.standard.username, USERS.standard.password);

      await expect(page).toHaveScreenshot('inventory-mobile.png', {
        fullPage: true,
        maxDiffPixelRatio: 0.05,
      });
    });

    test('inventory page on tablet viewport', async ({ page }) => {
      await page.setViewportSize({ width: 768, height: 1024 });

      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login(USERS.standard.username, USERS.standard.password);

      const items = page.locator('.inventory_item');
      await expect(items.first()).toBeVisible();
    });
  });

  test.describe('Visual Comparison Between Users', () => {
    test('visual user should display differently from standard user', async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login(USERS.visual.username, USERS.visual.password);

      await expect(page).toHaveScreenshot('visual-user-inventory.png', {
        fullPage: true,
        maxDiffPixelRatio: 0.05,
      });
    });
  });
});
