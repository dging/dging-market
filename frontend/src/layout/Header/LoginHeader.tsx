import React, { useState } from 'react';
import { styled, useTheme } from 'styled-components';
import { Arrange, Alert } from '../../components';
import { AccountSettingModal } from '../Modal';
import { useMainModal } from '../../recoil/MainModal/useMainModal';
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
  const { showMainModal, setShowMainModal } = useMainModal();
  const [isLogin, setIsLogin] = useState<boolean>(false);

  return (
    <>
      {showMainModal && <AccountSettingModal />}
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
                <AuthBtn onClick={() => setIsLogin(false)}>로그아웃</AuthBtn>
                <Alert />
              </Arrange>
            </>
          ) : (
            <AuthBtn
              onClick={() => {
                setShowMainModal(true);
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
