import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { USERS } from '../../fixtures/test-data';

test.describe('Login Functionality', () => {
  test('should login successfully with standard user', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    const inventoryPage = new InventoryPage(page);
    await expect(inventoryPage.inventoryItems.first()).toBeVisible();
    await expect(page).toHaveURL(/inventory\.html/);
  });

  test('should show error for locked out user', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.lockedOut.username, USERS.lockedOut.password);

    const errorMessage = await loginPage.getErrorMessage();
    expect(errorMessage).toContain('Epic sadface: Sorry, this user has been locked out.');
  });

  test('should show error for empty username', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login('', USERS.standard.password);

    const errorMessage = await loginPage.getErrorMessage();
    expect(errorMessage).toContain('Username is required');
  });

  test('should show error for empty password', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, '');

    const errorMessage = await loginPage.getErrorMessage();
    expect(errorMessage).toContain('Password is required');
  });

  test('should show error for invalid credentials', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login('invalid_user', 'wrong_password');

    const errorMessage = await loginPage.getErrorMessage();
    expect(errorMessage).toContain('Username and password do not match');
  });

  test('should logout successfully', async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);

    await page.click('#react-burger-menu-btn');
    await page.click('#logout_sidebar_link');

    await expect(loginPage.loginButton).toBeVisible();
    await expect(page).not.toHaveURL(/inventory\.html/);
  });

  test.describe('Login Page Visual Elements', () => {
    test('should display login form elements', async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.goto();

      await expect(loginPage.usernameInput).toBeVisible();
      await expect(loginPage.passwordInput).toBeVisible();
      await expect(loginPage.loginButton).toBeVisible();
      await expect(loginPage.appLogo).toBeVisible();
    });

    test('should clear error message when closing', async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login('', '');

      await expect(loginPage.errorMessage).toBeVisible();
      await page.locator('.error-button').click();
      await expect(loginPage.errorMessage).not.toBeVisible();
    });
  });
});
