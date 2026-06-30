Cypress.Commands.add('login', (username, password) => {
  cy.visit('/')
  cy.get('[data-test="username"]').type(username)
  cy.get('[data-test="password"]').type(password, { log: false })
  cy.get('[data-test="login-button"]').click()
})

Cypress.Commands.add('addToCart', (index) => {
  cy.get('.inventory_item').eq(index).within(() => {
    cy.get('button').contains('Add to cart').click()
  })
})

Cypress.Commands.add('checkCartBadge', (count) => {
  cy.get('.shopping_cart_badge').should('have.text', String(count))
})
