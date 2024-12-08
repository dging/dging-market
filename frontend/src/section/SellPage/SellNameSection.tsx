import React, { useState, useMemo, useRef, useEffect } from 'react';
import { useLocation, useSearchParams } from 'react-router-dom';
import { useRecoilState, useResetRecoilState } from 'recoil';
import styled, { useTheme } from 'styled-components';
import { Arrange, SpanGray } from '../../components/Base';
import { RadioBtn, RoundBtn } from '../../components/Button';
import { RoundCategory, SellRoundCategory } from '../../components/Category';
import { UnderlineTitle, BarTitle } from '../../components/Title';
import { AddImage } from '../../components/Input';
import { category1, category2, category3 } from '../../utils/_data';
import { onPressEnter } from '../../utils/onPressEnter';
import { SellName } from '../../recoil/sell/atom';
import { useSell } from '../../recoil/sell/useSell';

const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const TitleInput = styled.input<{ width?: string }>`
  box-sizing: border-box;
  width: ${(props) =>
    props.width ? props.width : props.theme.page_size.width};
  height: 67px;
  padding: 20px;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.body18};

  &:focus {
    outline: none;
  }
`;

export default function SellNameSection() {
  const theme = useTheme();
  const [sellName, setSellName] = useRecoilState(SellName);

  return (
    <WrapContent>
      <BarTitle style={{ ...theme.font.r24 }}>상품명</BarTitle>

      <TitleInput
        placeholder='상품명을 입력해주세요.'
        maxLength={40}
        name='title'
        value={sellName}
        onChange={(e) => setSellName(e.target.value)}
      />

      <Arrange margin='0 0 0 auto'>{sellName.length}/40</Arrange>
    </WrapContent>
  );
}
