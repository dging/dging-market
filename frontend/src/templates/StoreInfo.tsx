import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { IncludeImgBtn } from '../components/Button';
import { StoreReviewCard } from '../components/Card';
import { BarTitle } from '../components/Title';
import { SmallStoreProfile } from '../components/Profile';
import RightArrowBlack from '../assets/images/RightArrowBlack.png';

const TitleH3 = styled.div`
  width: 100%;
  ${({ theme }) => theme.font.r18};
  color: ${({ theme }) => theme.color.black0};
`;

const Bar = styled.div<{ $status?: boolean }>`
  width: ${(props) => (props.$status ? '100%' : '285px')};
  height: 1px;
  background-color: ${({ theme }) => theme.color.black5};
`;

const WrapMoreReview = styled.div`
  display: flex;
  width: 100%;
  padding: 20px 0;
  border-top: 1px solid ${({ theme }) => theme.color.black5};
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
  justify-content: center;
`;

export default function StoreInfo() {
  const theme = useTheme();
  return (
    <Arrange
      padding='0 0 0 10px'
      display='flex'
      flexdirection='column'
      gap='50px'
    >
      <BarTitle style={{ width: '100%', ...theme.font.r18 }}>상점정보</BarTitle>

      <Arrange
        display='flex'
        flexdirection='column'
        gap='20px'
        alignitems='end'
      >
        <SmallStoreProfile />

        <TitleH3>상점후기</TitleH3>

        <StoreReviewCard />
        <Bar />
        <StoreReviewCard />
      </Arrange>
      <WrapMoreReview>
        <IncludeImgBtn
          $textheight='18px'
          text='상점후기 더보기'
          textstyle={{ color: theme.color.black0, ...theme.font.r16 }}
          $rightbgimg={RightArrowBlack}
          $rightimgwidth='16px'
          $rightimgheight='16px'
        />
      </WrapMoreReview>
    </Arrange>
  );
}
