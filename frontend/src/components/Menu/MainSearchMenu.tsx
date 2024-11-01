import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
import DropBox from '../DropBox/DropBox';
import Home from '../../assets/images/Home.png';
import RightArrowBlack from '../../assets/images/RightArrowBlack.png';
import { useNavigate } from 'react-router-dom';

const WrapMainSearchMenu = styled(Arrange)`
  background-color: ${({ theme }) => theme.color.pink1};
`;

const HomeText = styled(Arrange)`
  ${({ theme }) => theme.font.r14}
  padding-top: 1px;
`;

export default function MainSearchMenu(props: { type: string }) {
  const theme = useTheme();
  const navigate = useNavigate();

  const items = ['전체', 'CD', 'Vinyl', 'Cassette', 'DVD'];
  const items2 = [
    '전체',
    'POP',
    'K-POP',
    'J-POP',
    'Rock',
    '월드 뮤직',
    '발라드',
    'RAP/힙합',
    '알앤비/소울',
    '인디',
    '일렉트로닉/하우스',
    '뉴에이지',
    '재즈/블루스',
    '클래식',
    '종교/명상',
    '컴필레이션',
    '포크',
    '기타',
  ];

  const items3 = [
    '전체',
    'Rock',
    'British / Modern / Garage / Indie',
    'Metal / Hardcore',
    'Alternative / Grunge',
    'Hard Rock',
    'Punk',
    'Art / Progressive / Psychedelic',
    'Old Rock',
    'Rock 컴필레이션',
  ];

  console.log(props.type);

  return (
    <WrapMainSearchMenu
      width='100%'
      display='flex'
      justifycontent='center'
      minwidth={theme.page_size.minwidth}
      padding={`${theme.size.xxs} 0`}
    >
      <Arrange
        display='flex'
        width={theme.page_size.width}
        alignitems='center'
        gap='6px'
      >
        <ImgBtn as='div' width='16px' height='17px' $backgroundimage={Home} />
        <HomeText>홈</HomeText>
        <ImgBtn
          as='div'
          width='16px'
          height='16px'
          $backgroundimage={RightArrowBlack}
        />
        <DropBox items={items} type={props.type} />
        {props.type !== items[0] && (
          <>
            <ImgBtn
              as='div'
              width='16px'
              height='16px'
              $backgroundimage={RightArrowBlack}
            />
            <DropBox items={items2} type={items2[0]} />
          </>
        )}
        {((props.type !== items2[0] && items2.includes(props.type)) ||
          items3.includes(props.type)) && (
          <>
            <ImgBtn
              as='div'
              width='16px'
              height='16px'
              $backgroundimage={RightArrowBlack}
            />
            <DropBox items={items3} />
          </>
        )}
      </Arrange>
    </WrapMainSearchMenu>
  );
}
