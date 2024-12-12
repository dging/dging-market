import React from 'react';
import styled, { useTheme } from 'styled-components';
import { MainGoodsMenu } from '../section';
import { Arrange, MainCard, UnderlineTitle } from '../components';

const WrapCard = styled(Arrange)`
  width: 100%;
  grid-template-columns: repeat(4, 1fr);
`;

export default function MainPage() {
  const theme = useTheme();

  const data = [
    { id: '1', title: 'Test - Test', price: '20000', date: '12' },
    { id: '2', title: 'Test - Test', price: '30000', date: '15' },
    { id: '3', title: 'Test - Test', price: '40000', date: '19' },
    { id: '4', title: 'Test - Test', price: '50000', date: '145' },
    { id: '5', title: 'Test - Test', price: '60000', date: '17' },
    { id: '6', title: 'Test - Test', price: '70000', date: '18' },
    { id: '7', title: 'Test - Test', price: '80000', date: '15' },
  ];

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
          <UnderlineTitle font={theme.font.b32}>
            오늘 들어온 상품
          </UnderlineTitle>
          <WrapCard
            width='100%'
            display='grid'
            margin='50px 0 0 0'
            gap='50px'
            justifycontent='space-between'
          >
            {data.map((val, idx) => (
              <MainCard
                key={idx}
                id={val.id}
                title={val.title}
                price={val.price}
                date={val.date}
              />
            ))}
          </WrapCard>
        </Arrange>
      </Arrange>
    </>
  );
}
