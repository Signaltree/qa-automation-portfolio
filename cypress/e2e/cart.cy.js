describe('Cart', () => {
  beforeEach(() => {
    cy.login('standard_user', 'secret_sauce')
  })

  it('shows empty cart', () => {
    cy.get('.shopping_cart_link').click()
    cy.url().should('include', '/cart.html')
    cy.get('.cart_contents_container').should('be.visible')
  })

  it('reflects badge count after adding items', () => {
    cy.addToCart(0)
    cy.addToCart(1)
    cy.checkCartBadge(2)
  })

  it('removes item from cart page', () => {
    cy.addToCart(0)
    cy.get('.shopping_cart_link').click()
    cy.get('.cart_item').should('have.length', 1)
    cy.get('.cart_button').contains('Remove').click()
    cy.get('.cart_item').should('not.exist')
  })

  it('continues shopping from cart', () => {
    cy.addToCart(0)
    cy.get('.shopping_cart_link').click()
    cy.get('[data-test="continue-shopping"]').click()
    cy.url().should('include', '/inventory.html')
  })
})
