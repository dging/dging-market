import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import { MainSearchMenu } from '../components/Menu';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { MainCard } from '../components/Card';

const Title = styled.div`
  margin-bottom: ${({ theme }) => theme.size.xxxxxl};
  ${({ theme }) => theme.font.r32}
`;

const PinkSpan = styled.span`
  text-decoration: underline;
  color: ${({ theme }) => theme.color.pink100};
`;

const WrapCard = styled(Arrange)`
  width: 100%;
  grid-template-columns: repeat(4, 1fr);
`;

export default function MainSearchPage() {
  const theme = useTheme();
  const location = useLocation();
  const type = { ...location.state };


  const words = ['전체', '판매중', '예약중', '판매완료'];
  const items = ['전체', 'CD', 'Vinyl', 'Cassette', 'DVD'];

  return (
    <>
      <MainSearchMenu type={type.type} />

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
          <Title>
            <PinkSpan>{type.type}</PinkSpan>
            {type.type !== '전체' && <> 전체</>}상품
          </Title>
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
