import axios from 'axios';
import { getAccessToken, getRefreshToken } from '../auth/token';

export const authInstance = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    Authorization:
      'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTczNTg0MjUyOCwiZXhwIjoxNzM1OTI4OTI4fQ.Ga4Ww4VL3Wr3S9DMP2vnOq_Xk5chMY835ztSos5BGZQ',
  },
});
