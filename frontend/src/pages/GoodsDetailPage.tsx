import React from 'react';
import styled, { useTheme } from 'styled-components';
import { useLocation } from 'react-router-dom';
import { Arrange } from '../components/Base';
import { GoodsProfile } from '../components/Profile';
import { GoodsInfo, StoreInfo } from '../templates';

const WrapInfo = styled.div`
  display: grid;
  grid-template-columns: 767px 1px 373px;
  gap: 10px;
`;

const Bar = styled.div`
  width: 1px;
  height: 100%;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function GoodsDetailPage() {
  const location = useLocation();
  const theme = useTheme();

  return (
    <Arrange
      width={theme.page_size.width}
      margin='0 auto'
      padding='20px 0 100px 0'
    >
      <GoodsProfile />
      <WrapInfo>
        <GoodsInfo />
        <Bar />
        <StoreInfo />
      </WrapInfo>
    </Arrange>
  );
}
