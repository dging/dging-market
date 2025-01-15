import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { useSell } from '../recoil/sell/useSell';
import { useMainModal } from '../recoil/ModalRecoil/useMainModal';
import { useReviewModal } from '../recoil/reviewModal/useReviewModal';

export default function ResetRecoilComponent() {
  const location = useLocation();
  const { closeModal } = useReviewModal();
  const { resetState } = useSell();

  useEffect(() => {
    closeModal();
    resetState();
  }, [location.pathname]);

  return null;
}
