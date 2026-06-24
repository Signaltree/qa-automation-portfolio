# REST Assured — API Contract Testing

**18 tests** | Java 21 | Hamcrest | JSON Schema | POJO Serialization

## What This Demonstrates

| Skill | Implementation |
|-------|----------------|
| **CRUD Operations** | GET, POST, PUT, PATCH, DELETE on JSONPlaceholder |
| **JSON Schema Validation** | Schema validation for `/posts` and `/users` endpoints |
| **POJO Serialization** | Request/response mapping with Jackson |
| **Query Parameters** | Pagination (`_start`/`_limit`), filtering by `userId`/`name` |
| **Path Parameters** | Single resource by ID (`/posts/1`, `/users/1`) |
| **Error Handling** | 404 for non-existent resources |
| **Nested Objects** | Address + Geo + Company deserialization |
| **Hamcrest Matchers** | Rich assertions: `every`, `hasItem`, `containsString` |

## Test Structure

```
rest-assured/
├── pom.xml
├── src/test/
│   ├── java/
│   │   ├── api/
│   │   │   ├── PostsApiTest.java    # 10 tests (CRUD + schema + pagination)
│   │   │   └── UsersApiTest.java    # 8 tests (CRUD + nested objects + filters)
│   │   ├── model/
│   │   │   ├── Post.java
│   │   │   └── User.java           # + Address, Geo, Company
│   │   └── spec/
│   │       └── RequestSpec.java     # Base URI + content type config
│   └── resources/
│       └── schemas/
│           ├── post-schema.json
│           └── user-schema.json
```

## Run

```bash
mvn test
```

## Comparison

Same API tested across two frameworks:
- **Karate**: Cucumber/Gherkin style, 67 tests
- **REST Assured**: Java DSL, Hamcrest assertions, 18 tests
