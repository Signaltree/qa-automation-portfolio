import { test, expect } from '@playwright/test';
import AxeBuilder from '@axe-core/playwright';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { CheckoutPage } from '../../pages/CheckoutPage';
import { USERS } from '../../fixtures/test-data';

test.describe('Checkout Flow Accessibility @accessibility', () => {
  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart('Sauce Labs Backpack');
    await inventoryPage.goToCart();

    await page.locator('[data-test="checkout"]').click();
  });

  test('should have no critical or serious accessibility violations on checkout page', async ({ page }) => {
    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    expect(results.violations.filter(v => v.impact === 'critical' || v.impact === 'serious')).toEqual([]);
  });

  test('should have accessible form inputs with proper labels', async ({ page }) => {
    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    const formViolations = results.violations.filter(v =>
      v.id === 'label' || v.id === 'input-button-name' || v.id === 'select-name'
    );
    expect(formViolations).toEqual([]);
  });

  test('should announce validation errors accessibly', async ({ page }) => {
    const checkoutPage = new CheckoutPage(page);
    await checkoutPage.continueToOverview();

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    const errorViolations = results.violations.filter(v =>
      v.id === 'aria-required-children' ||
      v.id === 'aria-required-parent' ||
      v.id === 'aria-valid-attr-value' ||
      v.id === 'aria-valid-attr' ||
      v.id === 'color-contrast'
    );
    expect(errorViolations).toEqual([]);
  });
});
