import { APIRequestContext } from '@playwright/test';

const BASE_URL = 'https://www.saucedemo.com';

export async function loginViaAPI(request: APIRequestContext, username: string, password: string) {
  const response = await request.post(`${BASE_URL}/api/auth/login`, {
    data: { username, password },
  });
  return response;
}
