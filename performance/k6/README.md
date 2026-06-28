# k6 Performance Tests

[k6](https://k6.io) is an open-source load testing tool for measuring system performance under various load conditions.

## Running tests

All tests run via Docker (k6 is not installed locally):

```bash
docker run --rm -v $(pwd)/performance/k6:/scripts grafana/k6 run /scripts/smoke-test.js
docker run --rm -v $(pwd)/performance/k6:/scripts grafana/k6 run /scripts/load-test.js
docker run --rm -v $(pwd)/performance/k6:/scripts grafana/k6 run /scripts/stress-test.js
```

Or via npm (Linux/Mac):

```bash
npm run test:k6:smoke
npm run test:k6:load
npm run test:k6:stress
npm run test:k6:all
```

Report output (if configured) is saved to `reports/`.
