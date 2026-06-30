describe('Burger Menu', () => {
  beforeEach(() => {
    cy.login('standard_user', 'secret_sauce')
  })

  it('toggles menu open and closed', () => {
    cy.get('#react-burger-menu-btn').click({ force: true })
    cy.get('#react-burger-cross-btn').click({ force: true })
  })

  it('resets cart state on logout', () => {
    cy.get('#react-burger-menu-btn').click({ force: true })
    cy.get('#logout_sidebar_link').click({ force: true })
    cy.get('[data-test="login-button"]').should('be.visible')
  })
})
