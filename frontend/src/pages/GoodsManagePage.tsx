import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { GoodsManageCard } from '../components/Card';
import { DropBox } from '../components/DropBox';
import { Searchbar } from '../components/Input';
import { ImgBtn } from '../components/Button';
import { RoundCategory, TradeCategory } from '../components/Category';
import { words } from '../utils/_data';
import Filter from '../assets/images/Filter.png';

export default function GoodsManagePage() {
  const theme = useTheme();
  return (
    <Arrange width={theme.page_size.width} margin='0 auto'>
      <Arrange
        width='100%'
        display='flex'
        justifycontent='space-between'
        padding='20px 0'
      >
        <Searchbar />
        <Arrange display='flex' gap='10px' alignitems='center'>
          <RoundCategory words={words} />
          <DropBox width='74px' items={['10개', '20개', '30개']} />
        </Arrange>
      </Arrange>

      <Arrange
        width='100%'
        display='grid'
        gap='20px'
        padding='50px 0'
        style={{ gridTemplateColumns: 'repeat(2, 1fr)' }}
      >
        <GoodsManageCard />
        <GoodsManageCard />
        <GoodsManageCard />
        <GoodsManageCard />
        <GoodsManageCard />
        <GoodsManageCard />
      </Arrange>
    </Arrange>
  );
}
