import { atom } from 'recoil';

export const ShowModal = atom({
  key: 'ShowModal',
  default: false,
});

export const ModalInfo = atom({
  key: 'ModalInfo',
  default: {
    name: '',
    rate: 0,
    checkReview: [],
    detailReview: '',
    photoReview: [],
  },
});
