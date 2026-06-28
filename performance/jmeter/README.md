# JMeter Performance Tests

Performance test suite using Apache JMeter (auto-downloaded via jmeter-maven-plugin).

## Tests

- **SauceDemo Smoke Test** (`saucedemo-smoke.jmx`): 5 users, login flow with assertions
- **JSONPlaceholder API Load Test** (`jsonplaceholder-api.jmx`): 10 users, REST API CRUD operations

## Running

```bash
mvn verify -f performance/jmeter/pom.xml
```

JMeter and dependencies are auto-downloaded. Reports are generated in `target/jmeter/reports/`.
