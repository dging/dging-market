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
  padding: 10px;
  background-color: ${(props) =>
    props.status ? props.theme.color.pink100 : 'white'};
  border: 1px solid
    ${(props) =>
      props.status ? props.theme.color.pink100 : props.theme.color.black1};
  border-radius: ${({ theme }) => theme.borderRadius.s};
  font-size: 12px;
  font-family: ${(props) => (props.status ? 'NSBold' : 'NSRegular')};
  color: ${(props) => (props.status ? 'white' : props.theme.color.black0)};
  cursor: pointer;
`;

export default function Btn(props: BtnType) {
  const { margin, children, width, height, status, onClick } = props;

  return (
    <WrapBtn margin={margin}>
      <Button width={width} height={height} status={status} onClick={onClick}>
        {children}
      </Button>
    </WrapBtn>
  );
}
