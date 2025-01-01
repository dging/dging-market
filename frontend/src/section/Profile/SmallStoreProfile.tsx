import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, SpanBold, ImgBtn, IncludeImgBtn } from '../../components';

import { Test, UserPlus } from '../../assets/images';

const WrapFollowBtn = styled.button`
  display: flex;
  width: 100%;
  height: 38px;
  padding: 10px;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: 4px;
  background-color: white;
  justify-content: center;
  align-items: center;

  cursor: pointer;
`;

const Bar = styled.div<{ $status?: boolean }>`
  width: 100%;
  height: 1px;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function SmallStoreProfile() {
  const theme = useTheme();
  return (
    <Arrange
      display='flex'
      width='100%'
      padding='0 10px 0 0'
      flexdirection='column'
      gap='20px'
    >
      <Arrange display='flex' gap='20px'>
        <ImgBtn
          as='div'
          width='56px'
          height='56px'
          $backgroundimage={Test}
          borderradius='100px'
        />
        <Arrange>
          <Arrange
            margin='0 0 10px 0'
            style={{ color: theme.color.black0, ...theme.font.b16 }}
          >
            FDOPE
          </Arrange>
          <Arrange>
            <span style={{ color: theme.color.black2, ...theme.font.info14 }}>
              상품 <SpanBold>8</SpanBold>
            </span>
            &nbsp;&nbsp;
            <span style={{ color: theme.color.black5, ...theme.font.info14 }}>
              |
            </span>
            &nbsp;&nbsp;
            <span style={{ color: theme.color.black2, ...theme.font.info14 }}>
              팔로워 <SpanBold>3</SpanBold>
            </span>
          </Arrange>
        </Arrange>
      </Arrange>
      <WrapFollowBtn>
        <IncludeImgBtn
          as='div'
          $leftbgimg={UserPlus}
          $leftimgwidth='18px'
          $leftimgheight='18px'
          text='팔로우'
          textstyle={{ color: theme.color.black2, ...theme.font.b14 }}
        />
      </WrapFollowBtn>
      <Bar />
    </Arrange>
  );
}
