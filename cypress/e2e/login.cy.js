describe('Login', () => {
  beforeEach(() => {
    cy.visit('/')
  })

  it('logs in with valid credentials', () => {
    cy.login('standard_user', 'secret_sauce')
    cy.url().should('include', '/inventory.html')
    cy.get('.inventory_list').should('be.visible')
  })

  it('shows error for locked out user', () => {
    cy.login('locked_out_user', 'secret_sauce')
    cy.get('[data-test="error"]').should('contain', 'locked out')
  })

  it('shows error for invalid credentials', () => {
    cy.login('invalid_user', 'wrong_password')
    cy.get('[data-test="error"]').should('contain', 'Username and password do not match')
  })

})
