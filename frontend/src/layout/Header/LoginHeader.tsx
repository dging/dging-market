import React, { useState } from 'react';
import { useTheme } from 'styled-components';
import { Arrange, Btn, Alert } from '../../components';

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
