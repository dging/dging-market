import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../Base';
import { Btn, ImgBtn, IncludeImgBtn } from '../Button';
import { DropBox } from '../DropBox';
import Test from '../../assets/images/Test.png';
import HeartPink from '../../assets/images/HeartPink.png';

const WrapCard = styled(Arrange)`
  width: 570px;
  border: 1px solid ${({ theme }) => theme.color.black5};
  border-radius: ${({ theme }) => theme.size.m};
`;

const Title = styled.div`
  width: 100%;
  max-width: 260px;
  height: 20px;
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.r18};
`;

const Price = styled(Arrange)`
  color: ${({ theme }) => theme.color.black0};
  ${({ theme }) => theme.font.eb18_ls2};
`;

const Unit = styled.span`
  margin-top: 2px;
  margin-left: 2px;
  ${({ theme }) => theme.font.b12_lh150};
`;

const Date = styled(Arrange)`
  ${({ theme }) => theme.font.info12};
  color: ${({ theme }) => theme.color.black2};
`;

const GraySpan = styled.span`
  color: ${({ theme }) => theme.color.black5};
`;

export default function GoodsManageCard() {
  const theme = useTheme();

  return (
    <WrapCard display='flex'>
      <ImgBtn
        as='div'
        width='180px'
        height='180px'
        $backgroundimage={Test}
        style={{ borderTopLeftRadius: '16px', borderBottomLeftRadius: '16px' }}
      />
      <Arrange
        display='flex'
        width='388px'
        height='180px'
        padding={theme.size.xl}
        flexdirection='column'
        justifycontent='space-between'
      >
        <Arrange display='flex' flexdirection='column' gap='10px'>
          <Title>Test - Test</Title>
          <Price width='100%' height='18px' display='flex' alignitems='center'>
            200,000<Unit>원</Unit>
          </Price>
          <Date display='flex' width='100%' alignitems='center'>
            수정&nbsp;&nbsp;2024-10-14 22:49&nbsp;&nbsp;<GraySpan>|</GraySpan>
            &nbsp;&nbsp;
            <IncludeImgBtn
              as='div'
              $leftimgwidth='12px'
              $leftimgheight='12px'
              $leftbgimg={HeartPink}
              $textheight='12px'
              text='100'
              textstyle={{ ...theme.font.b12 }}
              gap='2px'
            />
          </Date>
        </Arrange>

        <Arrange display='flex'>
          <DropBox items={['판매중', '예약중', '판매완료', '삭제']} />

          <Btn
            width='66px'
            padding={`10px ${theme.size.xxs} 8px ${theme.size.xxs}`}
            style={{ margin: '0 0 0 142px', ...theme.font.r14 }}
          >
            수정
          </Btn>
        </Arrange>
      </Arrange>
    </WrapCard>
  );
}
