import { test, expect } from '@playwright/test';
import AxeBuilder from '@axe-core/playwright';

test.describe('Login Page Accessibility @accessibility', () => {
  test('should have no critical or serious accessibility violations', async ({ page }) => {
    await page.goto('/');

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    expect(results.violations.filter(v => v.impact === 'critical' || v.impact === 'serious')).toEqual([]);
  });

  test('should not have color contrast violations', async ({ page }) => {
    await page.goto('/');

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    const contrastViolations = results.violations.filter(v => v.id === 'color-contrast');
    expect(contrastViolations).toEqual([]);
  });

  test('should have proper form labels and logo alt text', async ({ page }) => {
    await page.goto('/');

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    const formLabelViolations = results.violations.filter(v => v.id === 'label');
    expect(formLabelViolations).toEqual([]);

    const allResults = [...results.violations, ...results.incomplete];
    const altTextIssues = allResults.filter(r => r.id === 'image-alt');
    expect(altTextIssues).toEqual([]);
  });

  test('all violations should have impact less than critical', async ({ page }) => {
    await page.goto('/');

    const results = await new AxeBuilder({ page })
      .withTags(['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa'])
      .analyze();

    for (const violation of results.violations) {
      expect(['minor', 'moderate']).toContain(violation.impact);
    }
  });
});
