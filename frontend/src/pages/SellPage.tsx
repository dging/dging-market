import React, { useState, useMemo, useRef, useEffect } from 'react';
import { useLocation, useSearchParams } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { UnderlineTitle, BarTitle } from '../components/Title';
import { AddImage } from '../components/Input';

const WrapContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

export default function SellPage() {
  const theme = useTheme();
  return (
    <Arrange
      display='flex'
      width={theme.page_size.width}
      margin='0 auto'
      padding={`${theme.size.xxxxxl} 0 ${theme.size.xxxxxxl} 0`}
      flexdirection='column'
      gap='50px'
    >
      <UnderlineTitle>상품정보</UnderlineTitle>
      <WrapContent>
        <BarTitle title='상품이미지' style={{ ...theme.font.r24 }} />
        <AddImage />
      </WrapContent>
      <WrapContent>
        <BarTitle title='상품명' style={{ ...theme.font.r24 }} />
      </WrapContent>
      <WrapContent>
        <BarTitle title='카테고리' style={{ ...theme.font.r24 }} />
      </WrapContent>
      <WrapContent>
        <BarTitle title='상품상태' style={{ ...theme.font.r24 }} />
      </WrapContent>
      <WrapContent>
        <BarTitle title='설명' style={{ ...theme.font.r24 }} />
      </WrapContent>
      <WrapContent>
        <BarTitle title='태그' style={{ ...theme.font.r24 }} />
      </WrapContent>
      <UnderlineTitle>가격</UnderlineTitle>
      <UnderlineTitle>택배거래</UnderlineTitle>
      <WrapContent>
        <BarTitle title='배송비' style={{ ...theme.font.r24 }} />
      </WrapContent>
      <UnderlineTitle>추가정보</UnderlineTitle>
      <WrapContent>
        <BarTitle title='직거래' style={{ ...theme.font.r24 }} />
      </WrapContent>
      <WrapContent>
        <BarTitle title='수량' style={{ ...theme.font.r24 }} />
      </WrapContent>
    </Arrange>
  );
}
