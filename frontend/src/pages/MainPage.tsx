import React from 'react';
import { useTheme } from 'styled-components';
import TradeDetailMenu from '../components/Menu/TradeDetailMenu';
import RoundCategory from '../components/Category/RoundCategory';
import TradeCategory from '../components/Category/TradeCategory';
import DropBox from '../components/DropBox/DropBox';
import MystoreMenu from '../components/Menu/MystoreMenu';
import MainGoodsMenu from '../components/Menu/MainGoodsMenu';
import { Arrange } from '../components/Base';

export default function MainPage() {
  const theme = useTheme();
  const words = ['전체', '판매중', '예약중', '판매완료'];
  const items = ['전체', 'CD', 'Vinyl', 'Cassette', 'DVD'];

  return (
    <>
      <MystoreMenu />
      <TradeDetailMenu />
      <Arrange width='100%' display='flex' justifycontent='center'>
        <Arrange width={theme.page_size.width_s}>
          <MainGoodsMenu />
          MainPage
          <RoundCategory words={words} />
          <TradeCategory />
          <DropBox items={items} />
        </Arrange>
      </Arrange>
    </>
  );
}
