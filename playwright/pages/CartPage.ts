import { Page, Locator } from '@playwright/test';

export class CartPage {
  readonly page: Page;
  readonly checkoutButton: Locator;
  readonly cartItems: Locator;
  readonly continueShoppingButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.checkoutButton = page.locator('[data-test="checkout"]');
    this.cartItems = page.locator('.cart_item');
    this.continueShoppingButton = page.locator('[data-test="continue-shopping"]');
  }

  async getCartItemCount() {
    return this.cartItems.count();
  }

  async removeItem(itemName: string) {
    const item = this.page.locator('.cart_item', { hasText: itemName });
    await item.locator('button.cart_button').click();
  }

  async proceedToCheckout() {
    await this.checkoutButton.click();
  }
}
