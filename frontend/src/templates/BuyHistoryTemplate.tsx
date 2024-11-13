import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { BuyHistoryCard } from '../components/Card';
import { Searchbar } from '../components/Input';
import { ImgBtn } from '../components/Button';
import { RoundCategory, TradeCategory } from '../components/Category';
import { words } from '../utils/_data';
import Filter from '../assets/images/Filter.png';

export default function BuyHistoryTemplate() {
  const theme = useTheme();
  return (
    <Arrange width={theme.page_size.width} margin='0 auto'>
      <Arrange
        width='100%'
        display='flex'
        justifycontent='space-between'
        padding='20px 0'
      >
        <Arrange display='flex' gap='10px'>
          <Searchbar />
          <ImgBtn width='46px' height='46px' $backgroundimage={Filter} />
        </Arrange>
        <TradeCategory />
      </Arrange>
      <RoundCategory words={words} />
      <Arrange
        width='100%'
        display='grid'
        gap='20px'
        padding='50px 0'
        style={{ gridTemplateColumns: 'repeat(2, 1fr)' }}
      >
        <BuyHistoryCard />
        <BuyHistoryCard />
        <BuyHistoryCard />
        <BuyHistoryCard />
        <BuyHistoryCard />
        <BuyHistoryCard />
      </Arrange>
    </Arrange>
  );
}
