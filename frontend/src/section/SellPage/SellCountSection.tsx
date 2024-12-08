import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { useRecoilState } from 'recoil';
import { BarTitle } from '../../components/Title';
import { SellInput } from '../../components/Input';
import { SellCount } from '../../recoil/sell/atom';

const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

export default function SellCountSection() {
  const theme = useTheme();
  const [sellCount, setSellCount] = useRecoilState(SellCount);

  return (
    <WrapContent>
      <BarTitle style={{ ...theme.font.r24 }}>수량</BarTitle>
      <SellInput
        value={sellCount}
        setValue={setSellCount}
        unit='개'
        maxLength={3}
      />
    </WrapContent>
  );
}
