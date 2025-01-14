import axios from 'axios';
import { Cookies } from 'react-cookie';

const cookies = new Cookies();

console.log(`Bearer ${cookies.get('access_token')}`);

export const authInstance = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  // headers: {
  //   Authorization: `Bearer ${() => cookies.get('access_token')}`,
  // },
  headers: {
    Authorization: `Bearer ${cookies.get('access_token')}`,
  },
});
