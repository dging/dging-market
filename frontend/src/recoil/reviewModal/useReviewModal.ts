import react from 'react';
import { useRecoilState, useResetRecoilState } from 'recoil';
import { ShowModal, ModalInfo } from './atom';

export const useReviewModal = () => {
  const [showModal, setShowModal] = useRecoilState(ShowModal);
  const [modalInfo, setModalInfo] = useRecoilState(ModalInfo);
  const resetModal = useResetRecoilState(ModalInfo);

  const closeModal = () => {
    setShowModal(false);
    resetModal();
  };

  const consoleModalInfo = () => {
    console.log(modalInfo);
  };

  return {
    showModal,
    setShowModal,
    modalInfo,
    setModalInfo,
    resetModal,
    closeModal,
    consoleModalInfo,
  };
};
