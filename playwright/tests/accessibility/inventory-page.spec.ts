import { test, expect } from '@playwright/test';
import AxeBuilder from '@axe-core/playwright';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { USERS } from '../../fixtures/test-data';

test.describe('Inventory Page Accessibility @accessibility', () => {
  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);
  });

  test('should have no critical or serious accessibility violations', async ({ page }) => {
    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    expect(results.violations.filter(v => v.impact === 'critical' || v.impact === 'serious')).toEqual([]);
  });

  test('should have proper heading hierarchy', async ({ page }) => {
    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    const headingViolations = results.violations.filter(v => v.id === 'heading-order');
    expect(headingViolations).toEqual([]);
  });

  test('should have proper landmark structure', async ({ page }) => {
    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    const landmarkViolations = results.violations.filter(v =>
      v.id === 'region' || v.id === 'landmark-oneo-main' || v.id === 'landmark-unique'
    );
    expect(landmarkViolations).toEqual([]);
  });

  test('should have sufficient button color contrast', async ({ page }) => {
    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    const contrastViolations = results.violations.filter(v => v.id === 'color-contrast');
    expect(contrastViolations).toEqual([]);
  });
});
