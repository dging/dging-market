import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import Arrange from '../../components/Base/Arrange';
import Btn from '../../components/Button/Btn';
import Alert from '../../components/Alert/Alert';
import { setState } from '../../utils/setState';

const WrapLoginHeader = styled(Arrange)`
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
`;

export default function LoginBar() {
  const theme = useTheme();
  const [isLogin, setIsLogin] = useState<boolean | null>(null);

  return (
    <WrapLoginHeader width='100%' display='flex' justifycontent='center'>
      <Arrange
        width={`${theme.page_size.width_s}`}
        padding={`${theme.size.xxs} 0`}
        display='flex'
        justifycontent='right'
      >
        {isLogin ? (
          <>
            <Btn onClick={() => setState(null, setIsLogin)}>로그아웃</Btn>
            <Alert />
          </>
        ) : (
          <Btn onClick={() => setState(true, setIsLogin)}>로그인</Btn>
        )}
      </Arrange>
    </WrapLoginHeader>
  );
}
