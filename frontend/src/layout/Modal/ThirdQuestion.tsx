import React from 'react';
import { useTheme } from 'styled-components';
import { Arrange, IncludeImgBtn } from '../../components';
import { LeftArrowGray, RightArrowPink } from '../../assets/images';

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
