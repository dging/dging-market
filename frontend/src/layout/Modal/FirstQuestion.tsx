import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { useRecoilState } from 'recoil';
import ReactStars from 'react-stars';
import { FirstQuestion } from '../Modal';
import { Arrange } from '../../components/Base';
import { ImgBtn, IncludeImgBtn } from '../../components/Button';
import { CheckBox } from '../../components/CheckBox';
import { ShowModal } from '../../recoil/reviewModal/atom';
import CloseGray from '../../assets/images/CloseGray.png';
import LeftArrowGray from '../../assets/images/LeftArrowGray.png';
import RightArrowPink from '../../assets/images/RightArrowPink.png';
import CheckBorderGray from '../../assets/images/CheckBorderGray.png';
import CheckBorderPink from '../../assets/images/CheckBorderPink.png';

export default function ThirdQuestion(props: {
  setValue: React.Dispatch<React.SetStateAction<number>>;
}) {
  const theme = useTheme();
  return (
    <>
      ThirdQuestion
      <Arrange width='100%' display='flex' justifycontent='space-between'>
        <IncludeImgBtn
          $leftbgimg={LeftArrowGray}
          $leftimgwidth='20px'
          $leftimgheight='20px'
          text='이전'
          textstyle={{
            marginTop: '2px',
            color: theme.color.black2,
            ...theme.font.r16,
          }}
          gap='0px'
          onClick={() => props.setValue(1)}
        />

        <IncludeImgBtn
          text='등록'
          textstyle={{
            marginTop: '2px',
            color: theme.color.pink100,
            ...theme.font.r16,
          }}
          $rightbgimg={RightArrowPink}
          $rightimgwidth='20px'
          $rightimgheight='20px'
          gap='0px'
          onClick={() => props.setValue(3)}
        />
      </Arrange>
    </>
  );
}
