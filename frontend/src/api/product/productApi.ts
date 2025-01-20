import { authInstance } from '../axios/authInstance';
import { instance } from '../axios/instance';
import { ProductSellType } from '../../types/productType';

export const getProductsAll = async () => {
  return await authInstance
    .get('/products')
    .then((res) => {
      const { content } = res.data;
      return content;
    })
    .catch((err) => {
      console.error(err);
      const { status } = err;
      return status;
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
      return err.status;
    });
};

export const postProductRegister = async (data: ProductSellType) => {
  console.log(data);
  return await authInstance
    .post(`/products`, data)
    .then((res) => {
      console.log(res);
      const { status } = res;
      return status;
    })
    .catch((err) => {
      console.error(err);
      const { status } = err;
      return status;
    });
};

export const getProductsFavorite = async () => {
  return await authInstance
    .get('/products/favorite')
    .then((res) => {
      const { content } = res.data;
      return content;
    })
    .catch((err) => {
      console.error('getProductsFavorite : ', err);
      return [];
    });
};
