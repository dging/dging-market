import React, { useState, useMemo, useRef, useEffect } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { RadioBtn } from '../components/Button';
import { UnderlineTitle, BarTitle } from '../components/Title';
import { AddImage } from '../components/Input';
import {
  SellCategorySection,
  SellCountSection,
  SellNameSection,
  SellDirectSection,
  SellPriceSection,
  SellTagSection,
} from '../section/SellPage';

import { useSell } from '../recoil/sell/useSell';

const Wrap = styled.div<{ gap?: number }>`
  display: flex;
  flex-direction: column;
  gap: ${(props) => props.gap + 'px'};
`;

const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const DescriptionTextarea = styled.textarea`
  box-sizing: border-box;
  width: 100%;
  height: 200px;
  padding: 20px;
  color: ${({ theme }) => theme.color.black0};
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  resize: none;
  ${({ theme }) => theme.font.body18};

  &:focus {
    outline: none;
  }
`;

const SellBtn = styled.button<{ $status: boolean }>`
  width: 166px;
  height: 60px;
  background-color: ${(props) =>
    props.$status ? props.theme.color.pink100 : props.theme.color.black1};
  border: none;
  border-radius: 4px;
  color: white;
  ${({ theme }) => theme.font.b18};
  cursor: pointer;
`;

export default function SellPage() {
  const theme = useTheme();

  const {
    sellDescription,
    setSellDescription,
    setSellState,
    setSellDeliveryFee,
    onClickSellRegister,
  } = useSell();

  return (
    <Arrange
      display='flex'
      width={theme.page_size.width}
      margin='0 auto'
      padding={`${theme.size.xxxxxl} 0 ${theme.size.xxxxxxl} 0`}
      flexdirection='column'
      gap='100px'
    >
      <Wrap gap={50}>
        <UnderlineTitle>상품정보</UnderlineTitle>

        <WrapContent>
          <BarTitle style={{ ...theme.font.r24 }}>상품이미지</BarTitle>
          <AddImage />
        </WrapContent>
      </Wrap>

      <SellNameSection />
      <SellCategorySection />

      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>상품상태</BarTitle>

        <RadioBtn
          name='status'
          inputValue={['최상', '상', '중']}
          setValue={setSellState}
        />
      </WrapContent>

      <WrapContent>
        <BarTitle style={{ ...theme.font.r24 }}>설명</BarTitle>

        <DescriptionTextarea
          placeholder='설명을 입력해주세요.'
          name='description'
          value={sellDescription}
          maxLength={2000}
          onChange={(e) => setSellDescription(e.target.value)}
        />

        <Arrange margin='0 0 0 auto'>{sellDescription.length}/2000</Arrange>
      </WrapContent>

      <SellTagSection />

      <Wrap gap={50}>
        <UnderlineTitle>가격</UnderlineTitle>
        <SellPriceSection />
      </Wrap>

      <Wrap gap={50}>
        <UnderlineTitle>택배거래</UnderlineTitle>

        <WrapContent>
          <BarTitle style={{ ...theme.font.r24 }}>배송비</BarTitle>
          <RadioBtn
            name='direct'
            inputValue={['배송비포함', '배송비미포함']}
            setValue={setSellDeliveryFee}
          />
        </WrapContent>
      </Wrap>

      <SellDirectSection />
      <SellCountSection />

      <Arrange display='flex' gap='10px' margin='0 0 0 auto'>
        <SellBtn $status={false}>임시저장</SellBtn>
        <SellBtn $status={true} onClick={onClickSellRegister}>
          등록하기
        </SellBtn>
      </Arrange>
    </Arrange>
  );
}
