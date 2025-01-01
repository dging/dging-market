import React from 'react';
import styled from 'styled-components';
import Arrange from '../Base/Arrange';
import { BtnType } from '../../types/types';

const Button = styled.button<BtnType>`
  box-sizing: border-box;
  width: ${(props) => props.width || 'fit-content'};
  height: ${(props) => props.height || 'fit-content'};
  color: ${({ theme }) => theme.color.black2};
  padding: ${({ theme }) => `4px ${theme.size.xxxs}`};
  background-color: white;
  border: 1px solid ${({ theme }) => theme.color.black1};
  border-radius: ${({ theme }) => theme.size.xxxxxs};
  ${({ theme }) => theme.font.r12};
  ${(props) => (props.as === 'div' ? '' : 'cursor: pointer')};
`;

export default function SmallBtn(props: BtnType) {
  return (
    <Button {...props} onClick={props.onClick} style={props.style}>
      <Arrange height='14px' display='flex' alignitems='center'>
        {props.children}
      </Arrange>
    </Button>
  );
}
