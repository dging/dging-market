import { atom, selector } from 'recoil';
import { MyStoreType } from '../../types/storeType';
import { getStoresMe } from '../../api/store/storeApi';

export const GetMyStoreData = atom({
  key: 'GetMyStoreData',
  default: {} as MyStoreType,
});

export const GetMyStoreId = atom<number>({
  key: 'GetMyStoreId',
  default: 0,
});

export const GetStoresMeData = atom({
  key: 'GetStoresMeData',
  default: {} as MyStoreType,
});

export const GetStoresMeId = atom<number>({
  key: 'GetStoresMeId',
  default: 0,
});

export const GetStoresMeSelector = selector({
  key: 'GetStoresMeSelector',
  get: async () => {
    try {
      const data = await getStoresMe();
      return data;
    } catch (err) {
      console.error('Recoil GetStoresMeSelector Error : ', err);
      return {};
    }
  },
  // set: ({ set }, newValue) => {
  //   console.log(newValue);
  //   set(GetStoresMeData, newValue);
  //   set(GetStoresMeId, newValue.id);
  // },
});
