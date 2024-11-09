import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import ReactStars from 'react-stars';
import { Arrange, SpanBold } from '../Base';
import { ImgBtn, SmallBtn, DeclarationBtn } from '../Button';
import Test from '../../assets/images/Test.png';
import UserPlus from '../../assets/images/UserPlus.png';

const WrapStoreProfile = styled(Arrange)`
  position: relative;
`;

const WrapStoreImage = styled.div<{ $bg?: string }>`
  width: 275px;
  height: 275px;
  border-radius: 4px;
  background-image: url(${(props) => props.$bg || null});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

const WrapLeftDetail = styled(Arrange)`
  color: white;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const LeftTitle = styled(Arrange)`
  ${({ theme }) => theme.font.r14}
  margin-bottom: 4px;
`;

const Info = styled(Arrange)`
  ${({ theme }) => theme.font.r12};
  margin-top: 4px;
`;

const ManageBtn = styled.button`
  display: flex;
  width: 120px;
  height: 38px;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
  border: 0;
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  background-color: #ffffffe6;
  padding: ${({ theme }) => `0 ${theme.size.s}`};
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.font.b14};
  cursor: pointer;
`;

export default function FollowerCard() {
  return (
    <WrapStoreProfile>
      <WrapStoreImage $bg={Test} />
      <WrapLeftDetail
        position='absolute'
        display='flex'
        width='275px'
        flexdirection='column'
      >
        <LeftTitle width='100%' textalign='center'>
          스트릿뱅크
        </LeftTitle>
        <Arrange width='100%' display='flex' justifycontent='center'>
          <ReactStars value={5} count={5} size={20} edit={false} />
        </Arrange>

        <Info width='100%' textalign='center'>
          상품&nbsp;&nbsp;<SpanBold>27553</SpanBold>
          &nbsp;&nbsp;|&nbsp;&nbsp;팔로워&nbsp;&nbsp;
          <SpanBold>23312</SpanBold>
        </Info>

        <Arrange width='100%' textalign='center' margin='16px 0 0 0'>
          <ManageBtn>
            <ImgBtn as='div' $backgroundimage={UserPlus} />
            <Arrange padding='2px 0 0 4px'>팔로우</Arrange>
          </ManageBtn>
        </Arrange>
      </WrapLeftDetail>
    </WrapStoreProfile>
  );
}
