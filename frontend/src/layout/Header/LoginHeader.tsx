/* eslint-disable react/prop-types */
import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import Arrange from '../../components/Base/Arrange';
import Btn from '../../components/Button/Btn';
import Alert from '../../components/Alert/Alert';

export default function LoginHeader() {
  const theme = useTheme();
  const [isLogin, setIsLogin] = useState<boolean | null>(null);

  return (
    <Arrange $bottom={true} width='100%' display='flex' justifycontent='center'>
      <Arrange
        width={`${theme.page_size.width}`}
        padding={`${theme.size.xxs} 0`}
        display='flex'
        justifycontent='right'
      >
        {isLogin ? (
          <>
            <Arrange display='flex' gap='4px'>
              <Btn onClick={() => setIsLogin(null)}>로그아웃</Btn>
              <Alert />
            </Arrange>
          </>
        ) : (
          <Btn onClick={() => setIsLogin(true)}>로그인</Btn>
        )}
      </Arrange>
    </Arrange>
  );
}