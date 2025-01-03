import axios, { AxiosInstance } from 'axios';
import { getAccessToken, getRefreshToken } from '../auth/token';

export const instance: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json',
  },
});
