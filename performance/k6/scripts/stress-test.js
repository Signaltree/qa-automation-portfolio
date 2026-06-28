import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

const BASE = 'https://www.saucedemo.com';

const failureRate = new Rate('failed_checks');

export const options = {
  stages: [
    { duration: '1m', target: 50 },
    { duration: '2m', target: 50 },
    { duration: '30s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<8000'],
    http_req_failed: ['rate<0.10'],
    failed_checks: ['rate<0.10'],
  },
};

export default function () {
  http.get(BASE);
  http.get(`${BASE}/static/css/main.8a7d64a1.css`);
  http.get(`${BASE}/static/js/main.bcf4bc5f.js`);
  sleep(1);
}
