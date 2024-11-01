import React from 'react';
import styled, { useTheme } from 'styled-components';
import { MainGoodsMenu, MainSearchMenu } from '../components/Menu';
import { Arrange } from '../components/Base';
import { MainCard } from '../components/Card';

export default function MainPage() {
  const theme = useTheme();
  const words = ['전체', '판매중', '예약중', '판매완료'];
  const items = ['전체', 'CD', 'Vinyl', 'Cassette', 'DVD'];

  const Title = styled.div`
    text-decoration: underline;
    margin-bottom: ${({ theme }) => theme.size.xxxxxl};
    ${({ theme }) => theme.font.r32}
  `;

  const WrapCard = styled(Arrange)`
    width: 100%;
    grid-template-columns: repeat(4, 1fr);
  `;

  return (
    <>
      <MainGoodsMenu />

      <Arrange
        width='100%'
        minwidth={theme.page_size.minwidth}
        display='flex'
        justifycontent='center'
      >
        <Arrange
          width={theme.page_size.width}
          padding={`${theme.size.xxxxxl} 0 ${theme.size.xxxxxxl} 0`}
        >
          <Title>오늘 들어온 상품</Title>
          <WrapCard
            width='100%'
            display='grid'
            gap='50px'
            justifycontent='space-between'
          >
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
          </WrapCard>
        </Arrange>
      </Arrange>
    </>
  );
}
