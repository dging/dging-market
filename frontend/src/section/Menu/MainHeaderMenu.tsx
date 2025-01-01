import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import styled from 'styled-components';
import { Arrange, ImgBtn, IncludeImgBtn } from '../../components';
import StoreMenu from '../Menu/StoreMenu';
import {
  DownArrowBlack,
  DiskBlack,
  MessageBlack,
  Store,
} from '../../assets/images';

const Bar = styled.div`
  width: 1px;
  height: 16px;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function MainHeaderMenu() {
  const navigate = useNavigate();
  const location = useLocation().pathname;
  const [isShow, setIsShow] = useState<boolean>(false);
  useEffect(() => {
    setIsShow(false);
  }, [location]);

  return (
    <Arrange display='flex' alignitems='center' gap='10px'>
      <IncludeImgBtn
        $leftbgimg={DiskBlack}
        $textheight='16px'
        text='판매하기'
        onClick={() => navigate('/sell')}
      />
      <Bar />
      <IncludeImgBtn
        $leftbgimg={MessageBlack}
        $textheight='16px'
        text='거래톡'
        onClick={() => navigate('/talk')}
      />
      <Bar />
      <Arrange
        position='relative'
        display='flex'
        justifycontent='center'
        alignitems='center'
        gap='4px'
      >
        <IncludeImgBtn
          $leftbgimg={Store}
          $textheight='16px'
          text='내상점'
          onClick={() => navigate('/mystore/goods')}
        />
        <ImgBtn
          width='16px'
          height='16px'
          $backgroundimage={DownArrowBlack}
          onClick={() => setIsShow(!isShow)}
        />

        {isShow && <StoreMenu />}
      </Arrange>
    </Arrange>
  );
}
