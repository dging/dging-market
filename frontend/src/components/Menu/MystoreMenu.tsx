import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import { getPath } from '../../utils/getPath';
import { IncludeImgBtn } from '../Button';
import { Arrange } from '../Base';
import DiskBlack from '../../assets/images/DiskBlack.png';
import DiskPink from '../../assets/images/DiskPink.png';
import LayersBlack from '../../assets/images/LayersBlack.png';
import LayersPink from '../../assets/images/LayersPink.png';
import ReceiptBlack from '../../assets/images/ReceiptBlack.png';
import ReceiptPink from '../../assets/images/ReceiptPink.png';

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
        <Arrange display='flex' gap='50px' padding={`${theme.size.xl} 0px`}>
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
            $change={getPath() === '/history'}
            $leftbgimg={ReceiptBlack}
            $leftbgchangeimg={ReceiptPink}
            text='구매 / 판매 내역'
            onClick={() => {
              setIsSelect('/history');
              navigate('/history');
            }}
          />
        </Arrange>
      </Arrange>
    </Arrange>
  );
}
