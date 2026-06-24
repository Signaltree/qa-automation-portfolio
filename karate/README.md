# Karate — API + Load + Security Testing

**67 API tests + Gatling load tests** | Java 21 | Maven | Scala

## What This Demonstrates

| Skill | Implementation |
|-------|----------------|
| **REST API Testing** | CRUD operations on JSONPlaceholder (posts, users, auth) |
| **Schema Validation** | JSON structure validation on all endpoints |
| **Negative Testing** | 400/401/404/500 scenarios, edge cases |
| **Data-Driven** | External JSON test data and security payloads (60+) |
| **Security Testing** | OWASP Top 10: XSS, SQLi, NoSQLi, command injection, JWT tampering, path traversal, SSRF, XXE, CORS, CSRF |
| **Compliance** | Ley 21.663 Art 9 CSIRT incident reporting contract tests |
| **Load Testing** | Gatling 3.9.5: rampUsers, constantUsersPerSec, maxDuration |
| **CI/CD Ready** | Maven profiles: `mvn test` (API), `mvn gatling:test -Pload-test` (load) |

## Test Breakdown

| Suite | Tests | File |
|-------|-------|------|
| Posts API | 9 | `features/api/posts.feature` |
| Users API | 7 | `features/api/users.feature` |
| Auth API | 8 | `features/api/auth.feature` |
| Security (OWASP) | 14 | `features/security/security.feature` |
| CSIRT Contract | 5 | `features/security/csirt-reporting-contract.feature` |
| Load Simulation | 6 | `features/load/api-simulation.feature` |
| **Total API** | **67** | all passing |

## Run

```bash
mvn test                          # All 67 API tests
mvn test -Dtest=ApiTest#testPosts # Single suite
mvn gatling:test -Pload-test      # Load test
```
