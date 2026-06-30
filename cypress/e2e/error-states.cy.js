describe('Error States', () => {
  it('handles performance glitch user', () => {
    cy.visit('/', { timeout: 15000 })
    cy.get('[data-test="username"]', { timeout: 15000 }).type('performance_glitch_user')
    cy.get('[data-test="password"]', { timeout: 15000 }).type('secret_sauce', { log: false })
    cy.get('[data-test="login-button"]', { timeout: 15000 }).click()
    cy.url().should('include', '/inventory.html')
    cy.get('.inventory_list').should('be.visible')
  })

  it('shows red lock indicators for locked out user', () => {
    cy.visit('/')
    cy.get('[data-test="username"]').type('locked_out_user')
    cy.get('[data-test="password"]').type('secret_sauce', { log: false })
    cy.get('[data-test="login-button"]').click()
    cy.get('[data-test="error"]').should('be.visible')
  })
})
