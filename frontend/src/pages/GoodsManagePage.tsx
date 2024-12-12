import React from 'react';
import { useTheme } from 'styled-components';
import {
  Arrange,
  DropBox,
  GoodsManageCard,
  RoundCategory,
  Searchbar,
} from '../components';
import { words } from '../utils/_data';

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
