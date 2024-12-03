import { atom } from 'recoil';

interface SellImageType {
  name: string;
  message: string;
}

interface SellCategory {
  first: string;
  second: string;
  third: string;
}

export const SellImage = atom({
  key: 'SellImage',
  default: [] as SellImageType[],
});

export const SellName = atom({
  key: 'SellName',
  default: '',
});

export const SellCategory = atom({
  key: 'SellCategory',
  default: {
    first: '',
    second: '',
    third: '',
  },
});

export const SellStates = atom({
  key: 'SellStates',
  default: '',
});

export const SellDescription = atom({
  key: 'SellDescription',
  default: '',
});

export const SellTag = atom({
  key: 'SellTag',
  default: ['test1'],
});

export const SellPrice = atom({
  key: 'SellPrice',
  default: '',
});

export const SellDeliveryFee = atom({
  key: 'SellDeliveryFee',
  default: '',
});

export const SellDirect = atom({
  key: 'SellDirect',
  default: '',
});

export const SellCount = atom({
  key: 'SellCount',
  default: '',
});

export const SellState = atom({
  key: 'SellState',
  default: {
    image: [],
    title: 'test',
    category: {
      first: '',
      second: '',
      third: '',
    },
    state: '',
    description: 'description test',
    tag: [],
    price: '',
    deliveryfee: '',
    direct: {
      state: false,
      address: '',
    },
    count: '',
  },
});
