import { test, expect } from '@playwright/test';
import { LoginPage } from '../../pages/LoginPage';
import { InventoryPage } from '../../pages/InventoryPage';
import { CartPage } from '../../pages/CartPage';
import { CheckoutPage } from '../../pages/CheckoutPage';
import { USERS, CHECKOUT_INFO, INVENTORY_ITEMS } from '../../fixtures/test-data';

test.describe('Cart and Checkout Flow', () => {
  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.goto();
    await loginPage.login(USERS.standard.username, USERS.standard.password);
  });

  test('should navigate to cart and display added items', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[0]);
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[1]);
    await inventoryPage.goToCart();

    const cartPage = new CartPage(page);
    const count = await cartPage.getCartItemCount();
    expect(count).toBe(2);
  });

  test('should remove item from cart page', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[0]);
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[1]);
    await inventoryPage.goToCart();

    const cartPage = new CartPage(page);
    await cartPage.removeItem(INVENTORY_ITEMS[0]);

    const count = await cartPage.getCartItemCount();
    expect(count).toBe(1);
  });

  test('should complete full checkout flow', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[0]);
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[2]);
    await inventoryPage.goToCart();

    const cartPage = new CartPage(page);
    await cartPage.proceedToCheckout();

    const checkoutPage = new CheckoutPage(page);
    await checkoutPage.fillInformation(
      CHECKOUT_INFO.firstName,
      CHECKOUT_INFO.lastName,
      CHECKOUT_INFO.postalCode
    );
    await checkoutPage.continueToOverview();

    const breakdown = await checkoutPage.getPriceBreakdown();
    expect(breakdown.itemTotal).toContain('Item total: $');
    expect(breakdown.tax).toContain('Tax: $');
    expect(breakdown.total).toContain('Total: $');

    await checkoutPage.finishOrder();
    const confirmation = await checkoutPage.getOrderConfirmation();
    expect(confirmation).toBe('Thank you for your order!');
  });

  test('should verify price calculation in checkout', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    const itemPrice = 29.99;
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[0]);
    await inventoryPage.goToCart();

    const cartPage = new CartPage(page);
    await cartPage.proceedToCheckout();

    const checkoutPage = new CheckoutPage(page);
    await checkoutPage.fillInformation(
      CHECKOUT_INFO.firstName,
      CHECKOUT_INFO.lastName,
      CHECKOUT_INFO.postalCode
    );
    await checkoutPage.continueToOverview();

    const breakdown = await checkoutPage.getPriceBreakdown();
    const itemTotal = parseFloat(breakdown.itemTotal!.replace('Item total: $', ''));
    const tax = parseFloat(breakdown.tax!.replace('Tax: $', ''));
    const total = parseFloat(breakdown.total!.replace('Total: $', ''));
    expect(itemTotal).toBeCloseTo(itemPrice, 2);
    expect(total).toBeCloseTo(itemTotal + tax, 2);
  });

  test('should require checkout information', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.addItemToCart(INVENTORY_ITEMS[0]);
    await inventoryPage.goToCart();

    const cartPage = new CartPage(page);
    await cartPage.proceedToCheckout();

    const checkoutPage = new CheckoutPage(page);
    await checkoutPage.continueToOverview();

    const error = page.locator('[data-test="error"]');
    await expect(error).toBeVisible();
    expect(await error.textContent()).toContain('First Name is required');
  });
});
