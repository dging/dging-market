import React from 'react';
import { styled } from 'styled-components';
import { BtnType } from '../../types/types';

// const WrapImageButton = styled.div<BtnDivType>`
//   display: inline-block;
//   width: fit-content;
//   height: fit-content;
//   margin: ${(props) => props.margin};
// `;

const ImageButton = styled.button<BtnType>`
  width: ${(props) => props.width || '24px'};
  height: ${(props) => props.height || '24px'};
  padding: 0;
  margin: 0;
  background-image: url(${(props) => props.$backgroundimage || null});
  background-position: ${(props) => props.$backgroundposition || '100%'};
  background-size: contain;
  background-repeat: no-repeat;
  border: ${(props) =>
    props.$status ? `1px solid ${props.theme.color.black1}` : 'none'};
  border-radius: ${(props) => props.borderradius || '0px'};
  background-color: transparent;
  ${(props) => (props.as === 'div' ? '' : 'cursor: pointer')}
`;

export default function ImgBtn(props: BtnType) {
  return (
    // <WrapImageButton margin={props.margin}>
    <ImageButton {...props} onClick={props.onClick}>
      {props.children}
    </ImageButton>
    // </WrapImageButton>
  );
}
