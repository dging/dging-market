import axios, { AxiosInstance } from 'axios';
import { getAccessToken, getRefreshToken } from '../auth/token';

export const instance: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});
