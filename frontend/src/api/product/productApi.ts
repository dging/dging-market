import { authInstance } from '../axios/authInstance';
import { instance } from '../axios/instance';

export const getProductsAll = async () => {
  return await authInstance
    .get('/products')
    .then((res) => {
      const { content } = res.data;
      return content;
    })
    .catch((err) => {
      console.error(err);
      return [];
    });
};

export const getProductsId = async (id: string) => {
  console.log(id);
  return await authInstance
    .get(`/products/${id}`)
    .then((res) => {
      // console.log('getProductsId', res);
      const { data } = res;
      return data;
    })
    .catch((err) => {
      console.error(err);
      return [];
    });
};
