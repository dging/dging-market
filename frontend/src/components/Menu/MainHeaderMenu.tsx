import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { styled, useTheme } from 'styled-components';
import ImgBtn from '../Button/ImgBtn';
import Arrange from '../Base/Arrange';
import StoreBoard from './StoreBoard';
import DiskBlack from '../../assets/images/DiskBlack.png';
import MessageBlack from '../../assets/images/MessageBlack.png';
import Store from '../../assets/images/Store.png';
import DownArrowBlack from '../../assets/images/DownArrowBlack.png';

const Bar = styled.div`
  width: 1px;
  height: 16px;
  background-color: ${({ theme }) => theme.color.black5};
`;

const NavigateToBtn = styled.button`
  width: fit-content;
  height: fit-content;
  border: none;
  display: flex;
  align-items: center;
  padding: 0;
  gap: 4px;
  background-color: transparent;
  ${({ theme }) => theme.font.r16}
  cursor: pointer;
`;

export default function MainHeaderMenu() {
  const navigate = useNavigate();
  const [isShow, setIsShow] = useState<boolean>(false);

  return (
    <Arrange display='flex' alignitems='center' gap='10px'>
      <NavigateToBtn onClick={() => navigate('/sell')}>
        <ImgBtn as='div' $backgroundimage={DiskBlack}></ImgBtn>
        <Arrange height='16px'>판매하기</Arrange>
      </NavigateToBtn>
      <Bar />
      <NavigateToBtn onClick={() => navigate('/talk')}>
        <ImgBtn as='div' $backgroundimage={MessageBlack}></ImgBtn>
        <Arrange height='16px'>거래톡</Arrange>
      </NavigateToBtn>
      <Bar />
      <NavigateToBtn
        onClick={() => setIsShow(!isShow)}
        style={{ position: 'relative' }}
      >
        <ImgBtn as='div' $backgroundimage={Store}></ImgBtn>
        <Arrange height='16px'>내상점</Arrange>
        <ImgBtn as='div' $backgroundimage={DownArrowBlack}></ImgBtn>
        {isShow && <StoreBoard />}
      </NavigateToBtn>
    </Arrange>
  );
}
