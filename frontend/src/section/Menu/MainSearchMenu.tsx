import React, { useState, useEffect, useMemo } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../../components/Base';
import { ImgBtn } from '../../components/Button';
import DropBox from '../../components/DropBox/DropBox';
import Home from '../../assets/images/Home.png';
import RightArrowBlack from '../../assets/images/RightArrowBlack.png';

const WrapMainSearchMenu = styled(Arrange)`
  background-color: ${({ theme }) => theme.color.pink1};
`;

const HomeText = styled(Arrange)`
  ${({ theme }) => theme.font.r14}
  padding-top: 1px;
`;

export default function MainSearchMenu() {
  const theme = useTheme();
  const navigate = useNavigate();
  const location = useLocation();
  const params = useMemo(
    () => new URLSearchParams(location.search),
    [location.search]
  );
  const type = { ...location.state }.type;
  console.log('MainSearchMenu : ', type);

  const items1 = ['전체', 'CD', 'Vinyl', 'Cassette', 'DVD'];
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

  const [firstParam, setFirstParam] = useState(params.get('first'));
  const [secondParam, setSecondParam] = useState(params.get('second'));
  const [thirdParam, setThirdParam] = useState(params.get('third'));

  useEffect(() => {
    setFirstParam(params.get('first'));
    setSecondParam(params.get('second'));
    setThirdParam(params.get('third'));
  }, [params]);

  // console.log('MainSearchMenu : ', props.type);

  const onClickFirst = (first?: string) => {
    console.log('onClickFirst : ', type);

    const params = new URLSearchParams({
      first: first || items1[0],
    });
    navigate(`/category?${params.toString()}`);
  };

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
        {/* 첫번째 Dropdown */}
        <DropBox
          items={items1}
          type={firstParam || items1[0]}
          onClick={() => onClickFirst('CD')}
        />
        {firstParam !== items1[0] && (
          <>
            <ImgBtn
              as='div'
              width='16px'
              height='16px'
              $backgroundimage={RightArrowBlack}
            />
            {/* 두번째 Dropdown */}
            <DropBox items={items2} type={items2[0]} />
          </>
        )}
        {firstParam !== items1[0] && secondParam !== items2[0] && (
          <>
            <ImgBtn
              as='div'
              width='16px'
              height='16px'
              $backgroundimage={RightArrowBlack}
            />
            {/* 세번째 Dropdown */}
            <DropBox items={items3} type={items3[0]} />
          </>
        )}
      </Arrange>
    </WrapMainSearchMenu>
  );
}
