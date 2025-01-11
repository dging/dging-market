import React, { useState } from 'react';
import { styled, useTheme } from 'styled-components';
import { Arrange, Alert } from '../../components';
import { LoginModal } from '../Modal';
import { useMainModal } from '../../recoil/ModalRecoil/useMainModal';
import { authInstance } from '../../api/axios/authInstance';

const AuthBtn = styled.button`
  box-sizing: border-box;
  width: fit-content;
  height: 38px;
  padding: 10px;
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  font-size: 16px;
  color: ${({ theme }) => theme.color.black0};
  cursor: pointer;
`;

export default function LoginHeader() {
  const theme = useTheme();
  const { showLoginModal, setShowLoginModal } = useMainModal();
  const [isLogin, setIsLogin] = useState<boolean>(false);

  const onClickLoginBtn = (status: string) => {
    switch (status) {
      case 'login':
        setShowLoginModal(true);
        document.body.style.overflowY = 'hidden';
        break;
      case 'logout':
        setShowLoginModal(false);
        document.body.style.overflowY = 'auto';
        break;
    }
  };

  return (
    <>
      {showLoginModal && <LoginModal />}
      <Arrange
        $bottom={true}
        width='100%'
        display='flex'
        justifycontent='center'
      >
        <Arrange
          width={`${theme.page_size.width}`}
          padding={`${theme.size.xxs} 0`}
          display='flex'
          justifycontent='right'
        >
          {isLogin ? (
            <>
              <Arrange display='flex' gap='4px'>
                <AuthBtn
                  onClick={() => {
                    onClickLoginBtn('logout');
                  }}
                >
                  로그아웃
                </AuthBtn>
                <Alert />
              </Arrange>
            </>
          ) : (
            <AuthBtn
              onClick={() => {
                onClickLoginBtn('login');
              }}
            >
              로그인
            </AuthBtn>
          )}
        </Arrange>
      </Arrange>
    </>
  );
}
