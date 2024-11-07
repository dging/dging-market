import React from 'react';
import styled from 'styled-components';
import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
import Declaration from '../../assets/images/Declaration.png';

const DeclarationButton = styled.button`
  display: flex;
  border: 0;
  background-color: white;
  padding: 0;
  align-items: center;
  gap: 2px;
  color: ${({ theme }) => theme.color.black2};
  ${({ theme }) => theme.color.black2}
`;

function DeclarationBtn() {
  return (
    <DeclarationButton>
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

export default DeclarationBtn;
