import React from 'react';
import styled from 'styled-components';
import {
  IncludeImgBtnType,
  RightImgType,
  LeftImgType,
} from '../../types/types';

const IncludeImgButton = styled.button<IncludeImgBtnType>`
  display: flex;
  width: ${(props) => props.width || 'fit-content'};
  height: ${(props) => props.height || 'fit-content'};
  padding: 0;
  border: none;
  background-color: ${(props) => props.bgcolor || 'white'};
  align-items: center;
  gap: ${(props) => props.gap || '4px'};
  cursor: ${(props) => (props.as === 'div' ? 'auto' : 'pointer')};
`;

const RightImg = styled.div<RightImgType>`
  width: ${(props) => props.$rightimgwidth || '24px'};
  height: ${(props) => props.$rightimgheight || '24px'};
  border: none;
  background-image: ${(props) =>
    props.$change
      ? `url(${props.$rightbgchangeimg})`
      : `url(${props.$rightbgimg})`};
  background-position: ${(props) => props.$rightbgposition || '100%'};
  background-size: contain;
  background-color: transparent;
`;

const Text = styled.div<IncludeImgBtnType>`
  width: ${(props) => props.$textwidth || 'fit-content'};
  height: ${(props) => props.$textheight || 'fit-content'};
  color: ${(props) =>
    (props.$change ? props.theme.color.pink100 : props.theme.color.black0) ||
    props.textcolor};
  font-family: ${(props) => (props.$change ? 'NSBold' : 'NSRegular')};
  font-size: ${(props) => props.fontsize || '16px'};
  ${(props) => props.font}
`;

const LeftImg = styled.div<LeftImgType>`
  width: ${(props) => props.$leftimgwidth || '24px'};
  height: ${(props) => props.$leftimgheight || '24px'};

  background-image: ${(props) =>
    props.$change
      ? `url(${props.$leftbgchangeimg})`
      : `url(${props.$leftbgimg})`};
  background-position: ${(props) => props.$leftbgposition || '100%'};
  background-size: contain;
  background-color: transparent;
`;

LeftImg.defaultProps = {
  $change: false,
  $leftbgimg: null,
  $leftbgchangeimg: null,
};

RightImg.defaultProps = {
  $change: false,
  $rightbgimg: null,
  $rightbgchangeimg: null,
};

export default function IncludeImgBtn(
  props: IncludeImgBtnType & RightImgType & LeftImgType
) {
  return (
    <IncludeImgButton
      {...props}
      style={props.mainstyle}
      onClick={props.onClick}
    >
      {props.$leftbgimg && <LeftImg {...props} />}

      <Text {...props} style={props.textstyle}>
        {props.text}
      </Text>
      {props.$rightbgimg && <RightImg {...props} />}
    </IncludeImgButton>
  );
}
