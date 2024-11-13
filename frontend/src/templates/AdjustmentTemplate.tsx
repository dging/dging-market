import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { AdjustHistoryCard } from '../components/Card';
import { Searchbar } from '../components/Input';
import { ImgBtn } from '../components/Button';
import { RoundCategory, TradeCategory } from '../components/Category';
import { words } from '../utils/_data';
import Filter from '../assets/images/Filter.png';

const Money = styled.div`
  display: flex;
  align-items: center;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.info14};
`;
const Price = styled.span`
  ${({ theme }) => theme.font.price20};
`;

const Unit = styled.span`
  ${({ theme }) => theme.font.info14};
`;

const Title = styled.div`
  margin-bottom: 20px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.b16};
`;

const WrapAccount = styled.div`
  display: flex;
  width: fit-content;
  height: fit-content;
  padding: 15px 20px;
  border-radius: 4px;
  background-color: ${({ theme }) => theme.color.black3};
  align-items: center;
  ${({ theme }) => theme.font.info16};
`;

const Icon = styled.div`
  width: 16px;
  height: 16px;
  margin-right: 10px;
  background-color: ${({ theme }) => theme.color.pink100};
  border-radius: 16px;
`;

const Account = styled.div`
  width: fit-content;
  height: 18px;
  margin-left: 20px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.info14};
`;

export default function AdjustmentTemplate() {
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

      <Arrange display='flex' gap='20px'>
        <WrapAccount>
          <Icon />
          <Arrange height='16px'>농협은행</Arrange>
          <Account>1234456545845 ∙ 김민희</Account>
        </WrapAccount>
        <Money>
          올해 번 금액&nbsp;&nbsp;:&nbsp;&nbsp;<Price>1,600,000</Price>
          <Unit>&nbsp;원</Unit>
        </Money>
      </Arrange>

      <Arrange
        display='flex'
        padding='50px 0'
        flexdirection='column'
        gap='50px'
      >
        <div>
          <Title>2024년 09월</Title>
          <Arrange
            width='100%'
            display='grid'
            gap='20px'
            style={{ gridTemplateColumns: 'repeat(2, 1fr)' }}
          >
            <AdjustHistoryCard status={true} />
            <AdjustHistoryCard status={true} />
            <AdjustHistoryCard />
            <AdjustHistoryCard />
            <AdjustHistoryCard status={true} />
            <AdjustHistoryCard />
          </Arrange>
        </div>

        <div>
          <Title>2024년 08월</Title>
          <Arrange
            width='100%'
            display='grid'
            gap='20px'
            style={{ gridTemplateColumns: 'repeat(2, 1fr)' }}
          >
            <AdjustHistoryCard status={true} />
            <AdjustHistoryCard status={true} />
            <AdjustHistoryCard />
            <AdjustHistoryCard />
            <AdjustHistoryCard status={true} />
            <AdjustHistoryCard />
          </Arrange>
        </div>
      </Arrange>
    </Arrange>
  );
}
