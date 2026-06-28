import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

const BASE = 'https://www.saucedemo.com';

const failureRate = new Rate('failed_checks');

export const options = {
  vus: 1,
  duration: '30s',
  thresholds: {
    http_req_duration: ['max<3000'],
    http_req_failed: ['rate<0.01'],
    failed_checks: ['rate<0.01'],
  },
};

export default function () {
  const homeRes = http.get(BASE);
  check(homeRes, {
    'homepage returns 200': (r) => r.status === 200,
    'homepage loads in <3s': (r) => r.timings.duration < 3000,
    'page contains Swag Labs': (r) => r.body.includes('Swag Labs'),
  });

  const cssRes = http.get(`${BASE}/static/css/main.8a7d64a1.css`);
  check(cssRes, {
    'CSS returns 200': (r) => r.status === 200,
    'CSS is text/css': (r) => (r.headers['Content-Type'] || '').includes('text/css'),
  });

  const jsRes = http.get(`${BASE}/static/js/main.bcf4bc5f.js`);
  check(jsRes, {
    'JS returns 200': (r) => r.status === 200,
    'JS is application/javascript': (r) =>
      (r.headers['Content-Type'] || '').includes('javascript'),
  });

  sleep(1);
}
