import React from 'react';
import styled from 'styled-components';
import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
import Declaration from '../../assets/images/Declaration.png';

const DeclarationButton = styled.button`
  display: flex;
  height: 18px;
  border: 0;
  background-color: white;
  padding: 0;
  align-items: center;
  gap: 2px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.color.black2};
  cursor: pointer;
`;

export default function DeclarationBtn() {
  return (
    <DeclarationButton onClick={() => console.log('신고')}>
      <ImgBtn
        as='div'
        width='18px'
        height='18px'
        $backgroundimage={Declaration}
      />
      <Arrange padding='2px 0 0 0'>신고하기</Arrange>
    </DeclarationButton>
  );
}
