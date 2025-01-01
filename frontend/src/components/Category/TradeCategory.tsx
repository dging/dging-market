import React, { useState } from 'react';
import { styled } from 'styled-components';
import { Arrange, Btn } from '../../components';

const WrapTradeCategory = styled(Arrange)`
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  overflow: hidden;
`;

const TradeButton = styled(Btn)`
  border: 0;
  background-color: ${(props) =>
    props.$status ? props.theme.color.pink100 : 'white'};
  border-radius: 0px;
  color: ${(props) => (props.$status ? 'white' : props.theme.color.black0)};
  padding: ${({ theme }) => `15px ${theme.size.xxs} 13px ${theme.size.xxs}`};
  ${(props) => (props.$status ? props.theme.font.b14 : props.theme.font.r14)}
`;

const Bar = styled.div`
  width: 1px;
  height: 38px;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function TradeCategory() {
  const [status, setStatus] = useState<string>('전체');

  return (
    <WrapTradeCategory display='flex' alignitems='center'>
      <TradeButton
        $status={status === '전체'}
        onClick={() => {
          setStatus('전체');
        }}
      >
        전체
      </TradeButton>
      <Bar />
      <TradeButton
        $status={status === '안전'}
        onClick={() => {
          setStatus('안전');
        }}
      >
        안전
      </TradeButton>
      <Bar />
      <TradeButton
        $status={status === '일반'}
        onClick={() => {
          setStatus('일반');
        }}
      >
        일반
      </TradeButton>
    </WrapTradeCategory>
  );
}
