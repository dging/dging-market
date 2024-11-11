import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { Arrange } from '../Base';
import { ImgBtn, DeclarationBtn, NavigateBtn } from '../Button';
import DefaultProfile from '../../assets/images/DefaultProfile.png';

const WrapCard = styled(Arrange)`
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
`;

const Profile = styled.div<{ $bgimg?: any; $location: boolean }>`
  width: ${(props) => (props.$location ? '72px' : '48px')};
  height: ${(props) => (props.$location ? '72px' : '48px')};
  border-radius: 100px;
  background-image: url(${(props) => props.$bgimg || { DefaultProfile }});
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
`;
const TitleH3 = styled.div`
  ${({ theme }) => theme.font.r18};
  color: ${({ theme }) => theme.color.black0};
`;

const TitleH4 = styled.div`
  ${({ theme }) => theme.font.r16};
  color: ${({ theme }) => theme.color.black2};
`;

const Body16 = styled.div`
  height: 48px;
  /* text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap; */
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.body16};
`;

const Date = styled(Arrange)`
  ${({ theme }) => theme.font.date14};
  color: ${({ theme }) => theme.color.black2};
`;

export default function StoreReviewCard(props: { $location?: string }) {
  const location = useLocation();
  const theme = useTheme();
  const [starScore, setStarScore] = useState(5);

  const isStoreReview = () => {
    return location.pathname === '/mystore/review' ? true : false;
  };

  return (
    <Arrange
      width='100%'
      display='flex'
      gap={isStoreReview() ? '20px' : '10px'}
    >
      <Profile $bgimg={DefaultProfile} $location={isStoreReview()} />
      <Arrange
        width='100%'
        display='flex'
        flexdirection='column'
        gap='20px'
        padding={isStoreReview() ? `${theme.size.xxxs} 0` : 0}
      >
        <Arrange width='100%' display='flex' flexdirection='column' gap='8px'>
          {isStoreReview() ? (
            <TitleH3>상점 1020938호</TitleH3>
          ) : (
            <TitleH4>상점 1020938호</TitleH4>
          )}
          <ReactStars value={starScore} size={20} edit={false} />
          {isStoreReview() && <NavigateBtn />}
        </Arrange>
        {isStoreReview() ? (
          <TitleH3>친절하시고 우체국택배까지^^</TitleH3>
        ) : (
          <Body16>
            친절하시고 우체국택배까지^^ 친절하시고 우체국택배까지^^ 친절하시고
            우체국택배까지^^
          </Body16>
        )}
        {isStoreReview() && <DeclarationBtn />}
      </Arrange>
      <Date width='100px' textalign='right'>
        1년 전
      </Date>
    </Arrange>
  );
}
