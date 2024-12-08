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

export const SellImage = atom<string[]>({
  key: 'SellImage',
  default: [],
});

export const SellName = atom({
  key: 'SellName',
  default: '',
});

export const SellCategory = atom<string[]>({
  key: 'SellCategory',
  default: ['', '', '', ''],
});

export const SellState = atom({
  key: 'SellState',
  default: '',
});

export const SellDescription = atom({
  key: 'SellDescription',
  default: '',
});

export const SellTag = atom<string[]>({
  key: 'SellTag',
  default: [],
});

export const SellPrice = atom({
  key: 'SellPrice',
  default: '',
});

export const SellProposal = atom({
  key: 'SellProposal',
  default: false,
});

export const SellDeliveryFee = atom({
  key: 'SellDeliveryFee',
  default: '',
});

export const SellAddress = atom({
  key: 'SellAddress',
  default: {
    zonecode: '',
    address: '',
    detailAddress: '',
  },
});

export const SellDirect = atom({
  key: 'SellDirect',
  default: '',
});

export const SellCount = atom({
  key: 'SellCount',
  default: '',
});

// export const SellState = atom({
//   key: 'SellState',
//   default: {
//     image: [],
//     title: 'test',
//     category: {
//       first: '',
//       second: '',
//       third: '',
//     },
//     state: '',
//     description: 'description test',
//     tag: ['tag1'],
//     price: '',
//     deliveryfee: '',
//     direct: {
//       state: false,
//       address: '',
//     },
//     count: '',
//   },
// });
