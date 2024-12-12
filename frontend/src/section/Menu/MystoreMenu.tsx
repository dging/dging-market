import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { Arrange, IncludeImgBtn } from '../../components';
import { getPath } from '../../utils/getPath';
import {
  DiskBlack,
  DiskPink,
  LayersBlack,
  LayersPink,
  ReceiptBlack,
  ReceiptPink,
} from '../../assets/images';

const Bar = styled.div`
  width: 1px;
  height: 16px;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function MystoreMenu() {
  const navigate = useNavigate();
  const theme = useTheme();
  const [isSelect, setIsSelect] = useState(getPath());

  return (
    <Arrange
      width='100%'
      minwidth='1200px'
      $bottom={true}
      display='flex'
      justifycontent='center'
    >
      <Arrange width={theme.page_size.width}>
        <Arrange
          display='flex'
          gap='50px'
          padding={`${theme.size.xl} 0px`}
          alignitems='center'
        >
          <IncludeImgBtn
            $change={getPath() === '/sell'}
            $leftbgimg={DiskBlack}
            $leftbgchangeimg={DiskPink}
            text='상품등록'
            onClick={() => {
              setIsSelect('/sell');
              navigate('/sell');
            }}
          />

          <Bar />

          <IncludeImgBtn
            $change={getPath() === '/goodsmanage'}
            $leftbgimg={LayersBlack}
            $leftbgchangeimg={LayersPink}
            text='상품관리'
            onClick={() => {
              setIsSelect('/goodsmanage');
              navigate('/goodsmanage');
            }}
          />

          <Bar />

          <IncludeImgBtn
            $change={getPath().includes('/history')}
            $leftbgimg={ReceiptBlack}
            $leftbgchangeimg={ReceiptPink}
            text='구매 / 판매 내역'
            onClick={() => {
              setIsSelect('/history/sell');
              navigate('/history/sell');
            }}
          />
        </Arrange>
      </Arrange>
    </Arrange>
  );
}
