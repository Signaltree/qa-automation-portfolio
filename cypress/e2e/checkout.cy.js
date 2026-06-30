describe('Checkout', () => {
  beforeEach(() => {
    cy.login('standard_user', 'secret_sauce')
    cy.addToCart(0)
    cy.get('.shopping_cart_link').click()
  })

  it('completes a purchase', () => {
    cy.get('[data-test="checkout"]').click()
    cy.get('[data-test="firstName"]').type('Alejandro')
    cy.get('[data-test="lastName"]').type('Adriazola')
    cy.get('[data-test="postalCode"]').type('123456')
    cy.get('[data-test="continue"]').click()
    cy.get('[data-test="finish"]').click()
    cy.get('.complete-header').should('contain', 'Thank you')
  })

  it('shows error with empty form fields', () => {
    cy.get('[data-test="checkout"]').click()
    cy.get('[data-test="continue"]').click()
    cy.get('[data-test="error"]').should('be.visible')
  })

  it('cancels checkout', () => {
    cy.get('[data-test="checkout"]').click()
    cy.get('[data-test="cancel"]').click()
    cy.url().should('include', '/cart.html')
  })

  it('shows correct total', () => {
    cy.get('.inventory_item_price').first().then(($price) => {
      const priceText = $price.text().replace('$', '')
      const price = parseFloat(priceText)
      cy.get('[data-test="checkout"]').click()
      cy.get('[data-test="firstName"]').type('Alejandro')
      cy.get('[data-test="lastName"]').type('Adriazola')
      cy.get('[data-test="postalCode"]').type('123456')
      cy.get('[data-test="continue"]').click()
      cy.get('.summary_subtotal_label').should('contain', price.toFixed(2))
    })
  })
})
