export const USERS = {
  standard: { username: process.env.STANDARD_USER || 'standard_user', password: process.env.PASSWORD || 'secret_sauce' },
  lockedOut: { username: process.env.LOCKED_OUT_USER || 'locked_out_user', password: process.env.PASSWORD || 'secret_sauce' },
  problem: { username: process.env.PROBLEM_USER || 'problem_user', password: process.env.PASSWORD || 'secret_sauce' },
  performanceGlitch: { username: process.env.PERFORMANCE_GLITCH_USER || 'performance_glitch_user', password: process.env.PASSWORD || 'secret_sauce' },
  error: { username: process.env.ERROR_USER || 'error_user', password: process.env.PASSWORD || 'secret_sauce' },
  visual: { username: process.env.VISUAL_USER || 'visual_user', password: process.env.PASSWORD || 'secret_sauce' },
} as const;

export const INVENTORY_ITEMS = [
  'Sauce Labs Backpack',
  'Sauce Labs Bike Light',
  'Sauce Labs Bolt T-Shirt',
  'Sauce Labs Fleece Jacket',
  'Sauce Labs Onesie',
  'Test.allTheThings() T-Shirt (Red)',
] as const;

export const SORT_OPTIONS = {
  nameAsc: 'Name (A to Z)',
  nameDesc: 'Name (Z to A)',
  priceAsc: 'Price (low to high)',
  priceDesc: 'Price (high to low)',
} as const;

export const CHECKOUT_INFO = {
  firstName: 'John',
  lastName: 'Doe',
  postalCode: '12345',
} as const;
