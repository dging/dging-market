import React, { useState, useMemo, useRef, useEffect } from 'react';
import { useLocation, useSearchParams } from 'react-router-dom';
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
  const [searchParams] = useSearchParams();

  const first = searchParams.get('first');
  const second = searchParams.get('second');
  const third = searchParams.get('third');

  const [change, setChange] = useState(first);

  useEffect(() => {
    console.log('first : ', first);
    setChange(first);
  }, [first]);

  useEffect(() => {
    console.log('second : ', second);
    setChange(second);
  }, [second]);

  useEffect(() => {
    console.log('third : ', third);
    setChange(third);
  }, [third]);

  return (
    <>
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
            <PinkSpan>{change}</PinkSpan>
            {change !== '전체' && <> 전체</>}상품
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
