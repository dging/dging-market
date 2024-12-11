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
import RightArrowGray from '../../assets/images/RightArrowGray.png';
import CheckBorderGray from '../../assets/images/CheckBorderGray.png';
import CheckBorderPink from '../../assets/images/CheckBorderPink.png';
import '../../styles/starStyles.css';

export default function ThirdQuestion(props: {
  setValue: React.Dispatch<React.SetStateAction<number>>;
}) {
  const theme = useTheme();
  return (
    <>
      <ReactStars count={5} size={30} color1='white' className='custom-star' />
      <Arrange width='100%' display='flex' margin='40px 0 0 0'>
        <IncludeImgBtn
          text='다음'
          textstyle={{
            color: theme.color.black2,
            ...theme.font.r16,
          }}
          $rightbgimg={RightArrowGray}
          $rightimgwidth='20px'
          $rightimgheight='20px'
          gap='0px'
          mainstyle={{ marginLeft: 'auto' }}
          onClick={() => props.setValue(1)}
        />
      </Arrange>
    </>
  );
}
