import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { ImgBtn, IncludeImgBtn } from '../Button';
import { Arrange } from '../Base';
import StoreMenu from './StoreMenu';

import DiskBlack from '../../assets/images/DiskBlack.png';
import MessageBlack from '../../assets/images/MessageBlack.png';
import Store from '../../assets/images/Store.png';
import DownArrowBlack from '../../assets/images/DownArrowBlack.png';

const Bar = styled.div`
  width: 1px;
  height: 16px;
  background-color: ${({ theme }) => theme.color.black5};
`;

export default function MainHeaderMenu() {
  const navigate = useNavigate();
  const [isShow, setIsShow] = useState<boolean>(false);

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
