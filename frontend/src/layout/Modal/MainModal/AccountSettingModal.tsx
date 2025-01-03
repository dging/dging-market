import React, { useState, Dispatch, SetStateAction } from 'react';
import { styled, useTheme } from 'styled-components';
import { Arrange, ImgBtn } from '../../../components';
import { LoginModal, WithdrawModal } from './';
import { useMainModal } from '../../../recoil/MainModal/useMainModal';
import {
  LeftArrowGray,
  CloseGray,
  RightArrowLightGray,
} from '../../../assets/images';

const ModalBackground = styled.div`
  position: absolute;
  width: 100%;
  height: 100vh;
  background-color: #00000080;
  z-index: 10;
`;

const WrapModal = styled.div`
  box-sizing: border-box;
  width: 560px;
  height: 760px;
  margin: 90px auto 0 auto;
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
  const { setShowMainModal } = useMainModal();
  const [title, setTitle] = useState('계정설정');
  const [status, setStatus] = useState('계정설정');

  const ModalMenu = () => {
    const menu = ['SNS 연동', '로그아웃', '탈퇴'];
    return (
      <Arrange width='100%' margin='50px 0 0 0'>
        {menu.map((val, idx) => (
          <Arrange
            key={idx}
            width='100%'
            height='64px'
            display='flex'
            justifycontent='space-between'
            padding='20px 0'
            $bottom
            onClick={() => {
              setStatus(val);
            }}
            style={{ cursor: 'pointer' }}
          >
            <Content>{val}</Content>
            <ImgBtn
              $backgroundimage={RightArrowLightGray}
              onClick={() => {
                setShowMainModal(false);
                setStatus('계정설정');
              }}
            />
          </Arrange>
        ))}
      </Arrange>
    );
  };

  {
    /* 맨 처음 메뉴 */
  }
  const ShowSelectModal = () => {
    switch (status) {
      case '계정설정':
        return <ModalMenu />;
      case 'SNS 연동':
        return <LoginModal />;
      case '로그아웃':
        console.log('로그아웃');
        return <ModalMenu />;
      case '탈퇴':
        return <WithdrawModal />;
      default:
        return 'error';
    }
  };

  return (
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
          {status === '계정설정' && <Title>{title}</Title>}
          <ImgBtn
            $backgroundimage={CloseGray}
            width='32px'
            height='32px'
            onClick={() => setShowMainModal(false)}
          />
        </Arrange>

        <ShowSelectModal />
      </WrapModal>
    </ModalBackground>
  );
}
