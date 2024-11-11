import React from 'react';
import styled, { useTheme } from 'styled-components';
import { useLocation } from 'react-router-dom';
import { Arrange } from '../components/Base';
import { GoodsProfile } from '../components/Profile';
import { GoodsInfo, StoreInfo } from '../templates';

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
      <Arrange display='flex'>
        <GoodsInfo />
        <StoreInfo />
      </Arrange>
    </Arrange>
  );
}
