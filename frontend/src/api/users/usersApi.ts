import { instance } from '../axios/instance';

export const postUserExpiration = (data: {
  accessToken: string;
  refreshToken: string;
}) => {
  instance
    .post('/users/token/expiration', data)
    .then((res) => {
      console.log(res);
      return res;
    })
    .catch((err) => {
      console.log(err);
    });
};
