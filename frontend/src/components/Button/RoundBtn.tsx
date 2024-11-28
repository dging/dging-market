import React from 'react';
import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import Btn from './Btn';
import { BtnType } from '../../types/types';

const RoundButton = styled(Btn)<BtnType>`
  min-width: ${(props) => (props.$location === '/sell' ? '55px' : '50px')};
  background-color: white;
  padding: ${(props) =>
    props.$location === '/sell' ? '10px 16px' : '9px 9px 8px'};
  border: 1px solid
    ${(props) =>
      props.$status ? props.theme.color.pink100 : props.theme.color.black0};
  border-radius: 100px;
  color: ${(props) =>
    props.$status ? props.theme.color.pink100 : props.theme.color.black0};

  ${(props) =>
    props.$location === '/sell'
      ? props.theme.font.category18
      : props.theme.font.category16}
`;

export default function RoundBtn(props: BtnType) {
  const path = useLocation().pathname;
  console.log(location);
  return (
    <RoundButton
      {...props}
      $location={path}
      onClick={props.onClick}
      style={props.style}
    >
      {props.children}
    </RoundButton>
  );
}
