import React from 'react';
import { styled } from 'styled-components';
import { BtnType, BtnDivType, BtnButtonType } from '../../types/types';

// const WrapImageButton = styled.div<BtnDivType>`
//   display: inline-block;
//   width: fit-content;
//   height: fit-content;
//   margin: ${(props) => props.margin};
// `;

const ImageButton = styled.button<BtnButtonType>`
  width: ${(props) => props.width || '24px'};
  height: ${(props) => props.height || '24px'};
  padding: 0;
  margin: 0;
  background-image: url(${(props) => props.$backgroundimage || null});
  background-position: ${(props) => props.$backgroundposition || '100%'};
  background-size: contain;
  border: ${(props) =>
    props.$status ? `1px solid props.theme.color.black1` : 'none'};
  background-color: transparent;
  ${(props) => (props.as === 'div' ? '' : 'cursor: pointer')}
`;

export default function ImgBtn(props: BtnType) {
  return (
    // <WrapImageButton margin={props.margin}>
    <ImageButton {...props}>{props.children}</ImageButton>
    // </WrapImageButton>
  );
}
