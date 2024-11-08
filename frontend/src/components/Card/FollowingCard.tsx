import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../Base';
import { ImgBtn, IncludeImgBtn } from '../Button';
import { CheckBox } from '../CheckBox';
import { BtnType } from '../../types/types';
import Test from '../../assets/images/Test.png';
import UserCheck from '../../assets/images/UserCheck.png';

const Ring = styled.div`
  width: 4px;
  height: 4px;
  border: 3px solid ${({ theme }) => theme.color.black0};
  border-radius: 10px;
`;

const FollowingImg = styled(ImgBtn)<BtnType>``;

const Name = styled(Arrange)`
  color: white;
  ${({ theme }) => theme.font.b16};
`;

const Info = styled(Arrange)`
  color: white;
  ${({ theme }) => theme.font.info12};
`;

const FollowingBtn = styled.button`
  display: flex;
  width: 120px;
  color: white;
  margin-top: 10px;
  padding: ${({ theme }) => theme.size.xxxs};
  background-color: #e61fea80;
  border: 1px solid #f9c7fa;
  border-radius: 4px;
  justify-content: center;
  /* cursor: pointer; */
`;

const WrapInner = styled(Arrange)`
  color: #ffffffe6;
  ${({ theme }) => theme.font.b14};
`;

export default function FollowingCard() {
  const theme = useTheme();
  return (
    <Arrange
      width='100%'
      display='flex'
      justifycontent='space-between'
      alignitems='center'
    >
      <Arrange
        display='flex'
        width='250px'
        height='250px'
        $backgroundimage={Test}
        justifycontent='center'
        alignitems='center'
        style={{ borderRadius: '4px' }}
      >
        <Arrange
          display='flex'
          flexdirection='column'
          gap='10px'
          justifycontent='center'
        >
          <Name width='100%' textalign='center'>
            스트릿뱅크
          </Name>
          <Info width='100%' textalign='center'>
            상품&nbsp;&nbsp;27553&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;팔로워&nbsp;&nbsp;23312
          </Info>

          <Arrange width='100%' display='flex' justifycontent='center'>
            <FollowingBtn>
              <WrapInner display='flex' gap='4px' alignitems='center'>
                <ImgBtn as='div' $backgroundimage={UserCheck} />
                <Arrange>팔로잉</Arrange>
              </WrapInner>
            </FollowingBtn>
          </Arrange>
        </Arrange>
      </Arrange>
      <Ring />
      <Ring />
      <Ring />
      <Arrange display='flex' gap='10px'>
        <ImgBtn as='div' width='250px' height='250px' $backgroundimage={Test} />
        <ImgBtn as='div' width='250px' height='250px' $backgroundimage={Test} />
        <ImgBtn as='div' width='250px' height='250px' $backgroundimage={Test} />
      </Arrange>
    </Arrange>
  );
}
