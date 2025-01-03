import { authInstance } from '../axios/authInstance';

export const getProductsAll = async () => {};
export const getProductsId = async (id: number) => {
  return await authInstance
    .get(`/products/${id}`)
    .then((res) => {
      console.log('getProductsId', res);
      return res;
    })
    .catch((err) => console.error(err));
};
