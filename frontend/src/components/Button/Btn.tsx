import React from 'react';
import { styled } from 'styled-components';
import { BtnType, BtnDivType, BtnButtonType } from '../../types/types';

const WrapBtn = styled.div<BtnDivType>`
  width: fit-content;
  height: fit-content;
  margin: ${(props) => props.margin || '0'};
`;

const Button = styled.button<BtnButtonType>`
  width: ${(props) => props.width || 'fit-content'};
  height: ${(props) => props.height || 'fit-content'};
  padding: ${({ theme }) => theme.size.xxs};
  background-color: ${(props) =>
    props.$status ? props.theme.color.pink100 : 'white'};
  border: 1px solid
    ${(props) =>
      props.$status ? props.theme.color.pink100 : props.theme.color.black1};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  color: ${(props) => (props.$status ? 'white' : props.theme.color.black0)};
  ${(props) => (props.$status ? props.theme.font.b12 : props.theme.font.r12)}
  cursor: pointer;
`;

export default function Btn(props: BtnType) {
  return (
    <WrapBtn margin={props.margin}>
      <Button {...props} onClick={props.onClick}>
        {props.children}
      </Button>
    </WrapBtn>
  );
}
