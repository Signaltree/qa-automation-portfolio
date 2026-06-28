# Flutter — Integration Tests

7 integration tests for a Flutter demo app with counter and todo list.

## Tests

| Test | What it verifies |
|------|------------------|
| App renders with title | UI structure |
| Counter starts at 0 | Initial state |
| Increment button | State change |
| Multiple increments | Repeated state |
| Add todo item | List CRUD: create |
| Toggle todo | List CRUD: update |
| Delete todo | List CRUD: delete |

## Run

```bash
cd flutter_app
flutter test
```
