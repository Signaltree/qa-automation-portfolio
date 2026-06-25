Feature: Security Mock Server
  Mock API que rechaza ataques OWASP Top 10 para testing.

Background:
  * configure cors = { allowedOrigins: ['*'], allowedMethods: ['GET','POST','PUT','DELETE','OPTIONS'] }

@reject-path-traversal
Scenario: pathMatches('/posts/{id}') && methodIs('get') && pathParams.id.contains('..')
  * karate.abort(404)

@reject-sqli
Scenario: pathMatches('/posts') && methodIs('get') && requestParams.q
  * karate.abort(400, { error: 'bad request' })

@reject-ssrf
Scenario: pathMatches('/posts') && methodIs('get') && requestParams.url
  * karate.abort(400, { error: 'ssrf blocked' })

@reject-mass-assignment
Scenario: pathMatches('/posts') && methodIs('post') && request.role
  * karate.abort(400, { error: 'mass assignment blocked' })

@reject-xml
Scenario: pathMatches('/posts') && methodIs('post') && requestHeaders['Content-Type'][0] != 'application/json'
  * karate.abort(400, { error: 'invalid content type' })

@valid-get-post
Scenario: pathMatches('/posts') && methodIs('get')
  * def responseStatus = 200
  * def response = read('classpath:data/test-data-mock.json')

@valid-get-post-by-id
Scenario: pathMatches('/posts/{id}') && methodIs('get')
  * def responseStatus = 200
  * def response = { userId: 1, id: 1, title: 'mock title', body: 'mock body' }

@valid-create-post
Scenario: pathMatches('/posts') && methodIs('post')
  * def response = request
  * def responseStatus = 201

@delete-post
Scenario: pathMatches('/posts/{id}') && methodIs('delete')
  * def responseStatus = 200

@auth-login
Scenario: pathMatches('/auth/login') && methodIs('post')
  * def auth = requestHeaders['Authorization']
  * if (auth && auth[0] && auth[0].contains('none')) karate.abort(401, { error: 'unauthorized' })
  * def responseStatus = 200
  * def response = { token: 'valid-mock-token' }

@health-check
Scenario: pathMatches('/health') && methodIs('get')
  * def responseStatus = 200
  * def response = { status: 'ok' }
