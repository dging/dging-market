import { authInstance, instance } from '../axios/axios';

export const getStoresOverview = async (id: string) => {
  console.log(id);
  return await authInstance(`/stores/${id}/overview`)
    .then((res) => {
      console.log(res.data);
      return res.data;
    })
    .catch((err) => {
      console.log(err);
    });
};

export const getStoresProducts = async (id: number) => {
  return await authInstance(`/stores/${id}/products`)
    .then((res) => {
      return res.data.content;
    })
    .catch((err) => {
      console.log(err);
      return {};
    });
};

export const getStoresFollowers = async (id: number) => {
  return await authInstance(`/stores/${id}/followers`)
    .then((res) => {
      console.log(res.data);
      return res.data.content;
    })
    .catch((err) => {
      console.log(err);
      return {};
    });
};

export const getStoresFollowings = async (id: number) => {
  return await authInstance(`/stores/${id}/followings`)
    .then((res) => {
      return res.data.content;
    })
    .catch((err) => {
      console.log(err);
      return {};
    });
};

export const getStoresProductsReviews = async (id: number) => {
  return await authInstance(`/stores/${id}/products/reviews`)
    .then((res) => {
      return res.data.content;
    })
    .catch((err) => {
      console.log(err);
      return {};
    });
};

export const getStoresMe = async () => {
  return await authInstance('/stores/me')
    .then((res) => {
      // console.log(res.data);
      return res.data;
    })
    .catch((err) => {
      console.log(err);
    });
};
