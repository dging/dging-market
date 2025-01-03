import { setCookie, getCookie, deleteCookie } from './cookie';

export const setAccessToken = (token: string) => {
  setCookie('accessToken', token);
};

export const getAccessToken = () => {
  return getCookie('accessToken');
};

export const deleteAccessToken = () => {
  deleteCookie('accessToken');
};

export const setRefreshToken = (token: string) => {
  setCookie('refreshToken', token);
};

export const getRefreshToken = () => {
  return getCookie('refreshToken');
};

export const deleteRefreshToken = () => {
  deleteCookie('refreshToken');
};
