Feature: Security Mock Server

Background:
  * configure cors = { allowedOrigins: ['https://trusted-origin.cl'], allowedMethods: ['GET','POST','PUT','DELETE','OPTIONS'] }
  * def abort = function(status, msg) { karate.set('responseStatus', status); karate.set('response', { error: msg }); karate.abort() }

Scenario: pathMatches('/posts') && methodIs('get')
  * if (requestParams && requestParams.q) abort(400, 'bad request')
  * if (requestParams && requestParams.file && requestParams.file[0].contains('..')) abort(400, 'path traversal blocked')
  * if (requestParams && requestParams.url) abort(400, 'ssrf blocked')
  * def responseStatus = 200
  * def response = read('classpath:data/test-data-mock.json')

Scenario: pathMatches('/posts/{id}') && methodIs('get')
  * def responseStatus = 200
  * def response = { userId: 1, id: 1, title: 'mock', body: 'body' }

Scenario: pathMatches('/posts') && methodIs('post')
  * def ct = requestHeaders['content-type']
  * if (ct && ct.toString().contains('xml')) abort(400, 'invalid content type')
  * if (request && request.role) abort(400, 'mass assignment blocked')
  * def response = request
  * def responseStatus = 201

Scenario: pathMatches('/posts/{id}') && methodIs('delete')
  * def responseStatus = 200

Scenario: pathMatches('/auth/login') && methodIs('post')
  * def auth = requestHeaders['x-auth-token']
  * if (auth && auth[0] && auth[0].toLowerCase().contains('none')) abort(401, 'unauthorized')
  * def responseStatus = 200
  * def response = { token: 'valid-mock-token' }

Scenario: pathMatches('/health') && methodIs('get')
  * def responseStatus = 200
  * def response = { status: 'ok' }
