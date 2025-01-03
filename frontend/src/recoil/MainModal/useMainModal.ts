import { useRecoilState, useResetRecoilState } from 'recoil';
import { ShowMainModal } from './atom';

export const useMainModal = () => {
  const [showMainModal, setShowMainModal] = useRecoilState(ShowMainModal);

  return {
    showMainModal,
    setShowMainModal,
  };
};
