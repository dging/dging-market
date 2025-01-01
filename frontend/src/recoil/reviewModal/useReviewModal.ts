import { useRecoilState, useResetRecoilState } from 'recoil';
import {
  ShowModal,
  ModalName,
  ModalRate,
  ModalCheckReview,
  ModalDetailReview,
  ModalPhotoReview,
} from './atom';

export const useReviewModal = () => {
  const [showModal, setShowModal] = useRecoilState(ShowModal);

  const [modalName, setModalName] = useRecoilState(ModalName);
  const [modalRate, setModalRate] = useRecoilState(ModalRate);
  const [modalCheckReview, setModalCheckReview] =
    useRecoilState(ModalCheckReview);
  const [modalDetailReview, setModalDetailReview] =
    useRecoilState(ModalDetailReview);
  const [modalPhotoReview, setModalPhotoReview] =
    useRecoilState(ModalPhotoReview);

  const resetModalName = useResetRecoilState(ModalName);
  const resetModalRate = useResetRecoilState(ModalRate);
  const resetModalCheckReview = useResetRecoilState(ModalCheckReview);
  const resetModalDetailReview = useResetRecoilState(ModalDetailReview);
  const resetModalPhotoReview = useResetRecoilState(ModalPhotoReview);

  const handleCheckReview = () => {
    if (modalCheckReview.every((val) => val.value === false)) {
      return 'stop';
    } else {
      return 'pass';
    }
  };

  const closeModal = () => {
    setShowModal(false);
    resetModalName();
    resetModalRate();
    resetModalCheckReview();
    resetModalDetailReview();
    resetModalPhotoReview();
  };

  const consoleModalInfo = () => {
    console.log('modalName : ', modalName);
    console.log('modalRate : ', modalRate);
    console.log('modalCheckReview : ', modalCheckReview);
    console.log('modalDetailReview : ', modalDetailReview);
    console.log('modalPhotoReview : ', modalPhotoReview);
  };

  return {
    showModal,
    setShowModal,

    modalName,
    setModalName,
    modalRate,
    setModalRate,
    modalCheckReview,
    setModalCheckReview,
    modalDetailReview,
    setModalDetailReview,
    modalPhotoReview,
    setModalPhotoReview,

    resetModalName,
    resetModalRate,
    resetModalCheckReview,
    resetModalDetailReview,
    resetModalPhotoReview,

    handleCheckReview,
    closeModal,
    consoleModalInfo,
  };
};
