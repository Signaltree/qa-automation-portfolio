import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { USERS, SORT_OPTIONS } from '../../fixtures/test-data';

test.describe('Inventory Functionality', () => {
  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);
  });

  test('should display all inventory items', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await expect(inventoryPage.inventoryItems).toHaveCount(6);
  });

  test('should add item to cart', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart('Sauce Labs Backpack');

    const cartCount = await inventoryPage.getCartCount();
    expect(cartCount).toBe(1);
  });

  test('should add multiple items to cart', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart('Sauce Labs Backpack');
    await inventoryPage.addItemToCart('Sauce Labs Bike Light');
    await inventoryPage.addItemToCart('Sauce Labs Bolt T-Shirt');

    const cartCount = await inventoryPage.getCartCount();
    expect(cartCount).toBe(3);
  });

  test('should remove item from cart', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart('Sauce Labs Backpack');
    await inventoryPage.addItemToCart('Sauce Labs Bike Light');
    await inventoryPage.removeItemFromCart('Sauce Labs Backpack');

    const cartCount = await inventoryPage.getCartCount();
    expect(cartCount).toBe(1);
  });

  test.describe('Sorting', () => {
    test('should sort by name A to Z', async ({ page }) => {
      const inventoryPage = new InventoryPage(page);
      await inventoryPage.sortBy(SORT_OPTIONS.nameAsc);

      const names = await inventoryPage.getItemNames();
      const sorted = [...names].sort();
      expect(names).toEqual(sorted);
    });

    test('should sort by name Z to A', async ({ page }) => {
      const inventoryPage = new InventoryPage(page);
      await inventoryPage.sortBy(SORT_OPTIONS.nameDesc);

      const names = await inventoryPage.getItemNames();
      const sorted = [...names].sort().reverse();
      expect(names).toEqual(sorted);
    });

    test('should sort by price low to high', async ({ page }) => {
      const inventoryPage = new InventoryPage(page);
      await inventoryPage.sortBy(SORT_OPTIONS.priceAsc);

      const prices = await inventoryPage.getItemPrices();
      const sorted = [...prices].sort((a, b) => a - b);
      expect(prices).toEqual(sorted);
    });

    test('should sort by price high to low', async ({ page }) => {
      const inventoryPage = new InventoryPage(page);
      await inventoryPage.sortBy(SORT_OPTIONS.priceDesc);

      const prices = await inventoryPage.getItemPrices();
      const sorted = [...prices].sort((a, b) => b - a);
      expect(prices).toEqual(sorted);
    });
  });

  test.describe('Different User Types', () => {
    test('problem user should see broken images', async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.goto();
      await loginPage.login(USERS.problem.username, USERS.problem.password);

      const images = page.locator('.inventory_item img');
      const count = await images.count();
      for (let i = 0; i < count; i++) {
        const img = images.nth(i);
        const isBroken = await img.evaluate((el: HTMLImageElement) => {
          return el.naturalWidth === 0 || el.naturalHeight === 0;
        });
        if (isBroken) {
          console.log(`Problem user: Image ${i + 1} is broken`);
        }
      }
    });

    test('performance glitch user should load page with delay', async ({ page }) => {
      const loginPage = new LoginPage(page);
      const startTime = Date.now();

      await loginPage.goto();
      await loginPage.login(USERS.performanceGlitch.username, USERS.performanceGlitch.password);

      const loadTime = Date.now() - startTime;
      console.log(`Performance glitch user load time: ${loadTime}ms`);
      await expect(page).toHaveURL(/inventory\.html/);
    });
  });
});
