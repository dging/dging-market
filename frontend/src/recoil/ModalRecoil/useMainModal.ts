import { useRecoilState, useResetRecoilState } from 'recoil';
import { ShowLoginModal, ShowMystoreModal } from './atom';

export const useMainModal = () => {
  const [showLoginModal, setShowLoginModal] = useRecoilState(ShowLoginModal);
  const [showMystoreModal, setShowMystoreModal] =
    useRecoilState(ShowMystoreModal);

  const handleLoginModal = (status: boolean) => {
    setShowLoginModal(status);
    if (status === true) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
    }
  };

  const handleMystoreModal = (status: boolean) => {
    setShowMystoreModal(status);
    if (status === true) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
    }
  };

  return {
    showLoginModal,
    setShowLoginModal,
    handleLoginModal,
    showMystoreModal,
    setShowMystoreModal,
    handleMystoreModal,
  };
};
