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
