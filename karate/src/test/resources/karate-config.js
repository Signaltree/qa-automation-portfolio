function fn() {
  var env = karate.env || 'dev';

  var config = {
    baseUrl: 'https://jsonplaceholder.typicode.com',
    env: env,
    timeout: 10000,
  };

  if (env === 'dev') {
    config.baseUrl = 'https://jsonplaceholder.typicode.com';
  }

  karate.configure('connectTimeout', config.timeout);
  karate.configure('readTimeout', config.timeout);
  karate.configure('ssl', true);

  return config;
}
