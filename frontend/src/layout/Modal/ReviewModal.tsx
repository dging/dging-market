import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { useReviewModal } from '../../recoil/reviewModal/useReviewModal';
import { FirstQuestion, SecondQuestion, ThirdQuestion } from '../Modal';
import { Arrange, Btn, ImgBtn } from '../../components';
import { CloseGray } from '../../assets/images';
import '../../styles/starStyles.css';

const BlackBackground = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  background-color: #00000099;
  top: 0;
  left: 0;
`;

const WrapReviewModal = styled.div`
  position: fixed;
  box-sizing: border-box;
  width: 400px;
  height: fit-content;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  border: 1px solid ${({ theme }) => theme.color.black5};
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
`;

export default function ReviewModal() {
  const theme = useTheme();
  const { showModal, setShowModal, modalInfo, resetModal, closeModal } =
    useReviewModal();
  const [order, setOrder] = useState(0);

  const questions = [
    `${modalInfo.name}님과의 거래에 만족하셨나요?`,
    `${modalInfo.name}님에게 후기를 남겨주세요!`,
    '소중한 후기를 더 자세히 적어주세요!',
    '후기가 성공적으로 등록되었습니다!',
  ];

  const QuestionList = () => {
    if (order === 0) {
      return <FirstQuestion setValue={setOrder} />;
    } else if (order === 1) {
      return <SecondQuestion setValue={setOrder} />;
    } else if (order === 2) {
      return <ThirdQuestion setValue={setOrder} />;
    } else if (order === 3) {
      return (
        <Btn
          $status={true}
          style={{ marginTop: '20px' }}
          onClick={() => {
            closeModal();
            setOrder(0);
          }}
        >
          확인
        </Btn>
      );
    }
  };

  return (
    <>
      {showModal && (
        <BlackBackground>
          <WrapReviewModal>
            <Arrange
              width='100%'
              display='flex'
              flexdirection='column'
              alignitems='center'
            >
              <ImgBtn
                $backgroundimage={CloseGray}
                margin='0 0 0 auto'
                onClick={() => {
                  closeModal();
                  setOrder(0);
                }}
              />
              <Arrange margin='0 0 20px' style={{ ...theme.font.r16 }}>
                {questions[order]}
              </Arrange>

              <QuestionList />
            </Arrange>
          </WrapReviewModal>
        </BlackBackground>
      )}
    </>
  );
}
