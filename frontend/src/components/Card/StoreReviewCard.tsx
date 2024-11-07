import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
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

const DeclarationBtn = styled.button`
  display: flex;
  height: fit-content;
  border: 0;
  background-color: white;
  padding: 0;
  align-items: center;
  gap: 2px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.color.black2}
  cursor: pointer;
`;

const ReviewTitle = styled(Arrange)`
  ${({ theme }) => theme.font.r18};
  color: ${({ theme }) => theme.color.black2};
`;

export default function StoreReviewCard() {
  const theme = useTheme();
  const [starScore, setStarScore] = useState(5);
  return (
    <Arrange display='flex' justifycontent='space-between'>
      <Arrange display='flex' gap='20px'>
        <Profile $bgimg={DefaultProfile} />
        <Arrange gap='20px' padding={`${theme.size.xxxs} 0`}>
          <Arrange>
            <TitleH3>상점 1020938호</TitleH3>
            <ReactStars value={starScore} size={20} edit={false} />
          </Arrange>
          <TitleH3>상점 1020938호</TitleH3>
        </Arrange>
        <DeclarationBtn>
          <ImgBtn
            as='div'
            width='18px'
            height='18px'
            $backgroundimage={Declaration}
          />
          <Arrange padding='2px 0 0 0'>신고하기</Arrange>
        </DeclarationBtn>
      </Arrange>
    </Arrange>
  );
}
