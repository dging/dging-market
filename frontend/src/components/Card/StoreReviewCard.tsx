import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { Arrange } from '../Base';
import { ImgBtn, DeclarationBtn, NavigateBtn } from '../Button';
import DefaultProfile from '../../assets/images/DefaultProfile.png';
import Declaration from '../../assets/images/Declaration.png';

const WrapCard = styled(Arrange)`
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
`;

const Profile = styled.div<{ $bgimg?: any }>`
  width: 72px;
  height: 72px;
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

const Date = styled(Arrange)`
  ${({ theme }) => theme.font.date14};
  color: ${({ theme }) => theme.color.black2};
`;

export default function StoreReviewCard() {
  const theme = useTheme();
  const [starScore, setStarScore] = useState(5);
  return (
    <Arrange width='100%' display='flex' gap='20px'>
      <Profile $bgimg={DefaultProfile} />
      <Arrange
        width='100%'
        display='flex'
        flexdirection='column'
        gap='20px'
        padding={`${theme.size.xxxs} 0`}
      >
        <Arrange width='100%' display='flex' flexdirection='column' gap='8px'>
          <TitleH3>상점 1020938호</TitleH3>
          <ReactStars value={starScore} size={20} edit={false} />
          <NavigateBtn />
        </Arrange>
        <TitleH3>친절하시고 우체국택배까지^^</TitleH3>
        <DeclarationBtn />
      </Arrange>
      <Date width='100px' textalign='right'>
        1년 전
      </Date>
    </Arrange>
  );
}
