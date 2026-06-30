describe('Inventory', () => {
  beforeEach(() => {
    cy.login('standard_user', 'secret_sauce')
  })

  it('displays product list', () => {
    cy.get('.inventory_item').should('have.length.at.least', 6)
    cy.get('.inventory_item_name').first().should('not.be.empty')
  })

  it('adds item to cart', () => {
    cy.addToCart(0)
    cy.checkCartBadge(1)
  })

  it('removes item from cart', () => {
    cy.addToCart(0)
    cy.get('.inventory_item').eq(0).within(() => {
      cy.get('button').contains('Remove').click()
    })
    cy.get('.shopping_cart_badge').should('not.exist')
  })

  it('opens product detail page', () => {
    cy.get('.inventory_item_name').first().click()
    cy.url().should('include', '/inventory-item.html')
    cy.get('.inventory_details').should('be.visible')
  })
})
