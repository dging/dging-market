import React from 'react';
import { styled } from 'styled-components';
import Arrange from '../Base/Arrange';
import { BtnType, BtnButtonType } from '../../types/types';

const Button = styled.button<BtnType>`
  box-sizing: border-box;
  width: ${(props) => props.width || 'fit-content'};
  height: ${(props) => props.height || 'fit-content'};
  padding: ${({ theme }) =>
    `11px ${theme.size.xxs} 9px ${theme.size.xxs}` || '0px'};
  background-color: ${(props) =>
    props.$status ? props.theme.color.pink100 : 'white'};
  border: 1px solid
    ${(props) =>
      props.$status ? props.theme.color.pink100 : props.theme.color.black1};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  color: ${(props) => (props.$status ? 'white' : props.theme.color.black0)};
  ${(props) => (props.$status ? props.theme.font.b12 : props.theme.font.r12)}
  ${(props) => (props.as === 'div' ? '' : 'cursor: pointer')}
`;

export default function Btn(props: BtnType) {
  return (
    // <WrapBtn margin={props.margin}>
    <Button {...props} onClick={props.onClick}>
      <Arrange height='16px' display='flex' alignitems='center'>
        {props.children}
      </Arrange>
    </Button>
    // </WrapBtn>
  );
}
