import { Page, Locator } from '@playwright/test';

export class InventoryPage {
  readonly page: Page;
  readonly inventoryItems: Locator;
  readonly shoppingCartBadge: Locator;
  readonly sortDropdown: Locator;
  readonly cartLink: Locator;

  constructor(page: Page) {
    this.page = page;
    this.inventoryItems = page.locator('.inventory_item');
    this.shoppingCartBadge = page.locator('.shopping_cart_badge');
    this.sortDropdown = page.locator('[data-test="product-sort-container"]');
    this.cartLink = page.locator('.shopping_cart_link');
  }

  async addItemToCart(itemName: string) {
    const item = this.page.locator('.inventory_item', { hasText: itemName });
    await item.locator('button.btn_inventory').click();
  }

  async removeItemFromCart(itemName: string) {
    const item = this.page.locator('.inventory_item', { hasText: itemName });
    await item.locator('button.btn_inventory').click();
  }

  async getCartCount() {
    const badge = this.shoppingCartBadge;
    if (await badge.isVisible()) {
      return parseInt(await badge.textContent() || '0', 10);
    }
    return 0;
  }

  async sortBy(option: string) {
    await this.sortDropdown.selectOption({ label: option });
  }

  async getItemNames(): Promise<string[]> {
    return this.page.locator('.inventory_item_name').allTextContents();
  }

  async getItemPrices(): Promise<number[]> {
    const prices = await this.page.locator('.inventory_item_price').allTextContents();
    return prices.map(p => parseFloat(p.replace('$', '')));
  }

  async goToCart() {
    await this.cartLink.click();
  }
}
