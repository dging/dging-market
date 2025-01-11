import React, { useState, Dispatch, SetStateAction, useCallback } from 'react';
import { styled, useTheme } from 'styled-components';
import { Arrange, ImgBtn } from '../../../components';
import WithdrawModal from './WithdrawModal';
import { useMainModal } from '../../../recoil/ModalRecoil/useMainModal';
import {
  LeftArrowGray,
  CloseGray,
  RightArrowLightGray,
} from '../../../assets/images';

const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  background-color: #00000080;
  z-index: 10;
`;

const WrapModal = styled.div`
  position: absolute;
  box-sizing: border-box;
  width: 560px;
  height: 760px;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
  padding: 30px 20px;
  background-color: ${({ theme }) => theme.color.black4};
  border-radius: 8px;
`;

const Title = styled.div`
  display: flex;
  width: fit-content;
  align-items: center;
  font-size: 24px;
  ${({ theme }) => theme.font.b24};
`;

const Content = styled.div`
  width: fit-content;
  height: fit-content;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18}
`;

export default function AccountSettingModal() {
  const { showMystoreModal, handleMystoreModal } = useMainModal();
  const [status, setStatus] = useState('계정설정');

  return (
    <>
      {showMystoreModal && (
        <ModalBackground>
          <WrapModal>
            {/* 뒤로가기 버튼, 제목, 닫기 버튼 */}
            <Arrange width='100%' display='flex' justifycontent='space-between'>
              {status === '계정설정' ? (
                <Arrange width='32px' height='32px'></Arrange>
              ) : (
                <ImgBtn
                  $backgroundimage={LeftArrowGray}
                  width='32px'
                  height='32px'
                  onClick={() => setStatus('계정설정')}
                />
              )}
              {status === '계정설정' && <Title>계정설정</Title>}
              <ImgBtn
                $backgroundimage={CloseGray}
                width='32px'
                height='32px'
                onClick={() => {
                  handleMystoreModal(false);
                }}
              />
            </Arrange>

            {status === '계정설정' ? (
              <Arrange width='100%' margin='50px 0 0 0'>
                <Arrange
                  width='100%'
                  height='64px'
                  display='flex'
                  justifycontent='space-between'
                  padding='20px 0'
                  $bottom
                  onClick={() => {
                    handleMystoreModal(false);
                  }}
                  style={{ cursor: 'pointer' }}
                >
                  <Content>로그아웃</Content>
                  <ImgBtn $backgroundimage={RightArrowLightGray} />
                </Arrange>

                <Arrange
                  width='100%'
                  height='64px'
                  display='flex'
                  justifycontent='space-between'
                  padding='20px 0'
                  $bottom
                  onClick={() => {
                    setStatus('탈퇴');
                  }}
                  style={{ cursor: 'pointer' }}
                >
                  <Content>탈퇴</Content>
                  <ImgBtn $backgroundimage={RightArrowLightGray} />
                </Arrange>
              </Arrange>
            ) : (
              <WithdrawModal />
            )}
          </WrapModal>
        </ModalBackground>
      )}
    </>
  );
}
