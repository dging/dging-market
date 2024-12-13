import React, { useState, useEffect, useRef } from 'react';
import { useLocation } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { useReviewModal } from '../../../recoil/reviewModal/useReviewModal';
import { FirstQuestion, SecondQuestion, ThirdQuestion } from './';
import { Arrange, Btn, ImgBtn } from '../../../components';
import { CloseGray } from '../../../assets/images';

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
  const path = useLocation().pathname;
  const outside = useRef<HTMLInputElement>(null);
  const { showModal, setShowModal, modalName, closeModal, consoleModalInfo } =
    useReviewModal();
  const [order, setOrder] = useState(0);

  const questions = [
    `${modalName}님과의 거래에 만족하셨나요?`,
    `${modalName}님에게 후기를 남겨주세요!`,
    '소중한 후기를 더 자세히 적어주세요!',
    '후기가 성공적으로 등록되었습니다!',
  ];

  useEffect(() => {
    console.log(path);
  }, [path]);

  return (
    <>
      {showModal && (
        <BlackBackground
          ref={outside}
          onClick={(e) => {
            if (e.target === outside.current) {
              closeModal();
            }
          }}
        >
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

              {order === 0 && <FirstQuestion key={0} setValue={setOrder} />}
              {order === 1 && <SecondQuestion key={1} setValue={setOrder} />}
              {order === 2 && <ThirdQuestion key={2} setValue={setOrder} />}
              {order === 3 && (
                <Btn
                  key={3}
                  $status={true}
                  style={{ marginTop: '20px' }}
                  onClick={() => {
                    closeModal();
                    setOrder(0);
                  }}
                >
                  확인
                </Btn>
              )}
            </Arrange>
          </WrapReviewModal>
        </BlackBackground>
      )}
    </>
  );
}
