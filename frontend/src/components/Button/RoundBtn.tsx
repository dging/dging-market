import React from 'react';
import { styled } from 'styled-components';
import Btn from './Btn';
import { BtnType } from '../../types/types';

const RoundButton = styled(Btn)<BtnType>`
  background-color: white;
  border: 1px solid
    ${(props) =>
      props.$status ? props.theme.color.pink100 : props.theme.color.black0};
  border-radius: 100px;
  color: ${(props) =>
    props.$status ? props.theme.color.pink100 : props.theme.color.black0};

  ${({ theme }) => theme.font.category16}
`;

export default function RoundBtn(props: BtnType) {
  return (
    <RoundButton {...props} onClick={props.onClick}>
      {props.children}
    </RoundButton>
  );
}
