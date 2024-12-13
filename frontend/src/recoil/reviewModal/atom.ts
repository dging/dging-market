import { atom } from 'recoil';

export const ShowModal = atom({
  key: 'ShowModal',
  default: false,
});

export const ModalName = atom({
  key: 'ModalName',
  default: '',
});

export const ModalRate = atom({
  key: 'ModalRate',
  default: 0,
});

export const ModalCheckReview = atom<{ content: string; value: boolean }[]>({
  key: 'ModalCheckReview',
  default: [
    { content: '😏 구매확정이 빨라요.', value: false },
    { content: '😎 거래톡 답변이 빨라요.', value: false },
    { content: '😇 친절하고 배려가 넘쳐요.', value: false },
    { content: '🤩 무리한 네고를 하지 않아요.', value: false },
    { content: '☺️ 꼭 필요한 문의만 해요.', value: false },
    { content: '선택할 항목이 없어요.', value: false },
  ],
});

export const ModalDetailReview = atom({
  key: 'ModalDetailReview',
  default: '',
});

export const ModalPhotoReview = atom({
  key: 'ModalPhotoReview',
  default: [],
});
