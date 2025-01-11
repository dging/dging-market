import { atom } from 'recoil';

export const ShowLoginModal = atom({
  key: 'ShowLoginModal',
  default: false,
});

export const ShowMystoreModal = atom({
  key: 'ShowMystoreModal',
  default: false,
});
