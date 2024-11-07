import React from 'react';
import styled from 'styled-components';
import Btn from './Btn';
import IncludeImgBtn from './IncludeImgBtn';
import RightArrowBlack from '../../assets/images/RightArrowBlack.png';

const NavigateButton = styled.button`
  padding: ${({ theme }) => `9px ${theme.size.xxxs} 7px ${theme.size.xxxs}`};
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  cursor: pointer;
`;

export default function NavigateBtn() {
  const text = {
    text: 'TEST - TEST',
  };
  return (
    <NavigateButton>
      <IncludeImgBtn
        as='div'
        gap='5px'
        fontsize='18px'
        $rightbgimg={RightArrowBlack}
        $rightimgwidth='18px'
        $rightimgheight='18px'
        text={text.text}
      />
    </NavigateButton>
  );
}
