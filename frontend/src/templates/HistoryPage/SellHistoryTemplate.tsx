import React from 'react';
import { useTheme } from 'styled-components';
import { ShowModal } from '../../recoil/reviewModal/atom';
import { useRecoilState } from 'recoil';
import { ReviewModal } from '../../layout/Modal';
import { Arrange } from '../../components/Base';
import { ImgBtn } from '../../components/Button';
import { Searchbar } from '../../components/Input';
import { SellHistoryCard } from '../../components/Card';
import { RoundCategory, TradeCategory } from '../../components/Category';
import { words } from '../../utils/_data';
import Filter from '../../assets/images/Filter.png';

export default function SellHistoryTemplate() {
  const [showModal, setShowModal] = useRecoilState(ShowModal);
  const theme = useTheme();
  return (
    <Arrange width={theme.page_size.width} margin='0 auto'>
      {showModal && <ReviewModal />}
      <Arrange
        width='100%'
        display='flex'
        justifycontent='space-between'
        padding='20px 0'
      >
        <Arrange display='flex' gap='10px'>
          <Searchbar />
          <ImgBtn width='46px' height='46px' $backgroundimage={Filter} />
        </Arrange>
        <TradeCategory />
      </Arrange>
      <RoundCategory words={words} />
      <Arrange
        width='100%'
        display='grid'
        gap='20px'
        padding='50px 0'
        style={{ gridTemplateColumns: 'repeat(2, 1fr)' }}
      >
        <SellHistoryCard />
        <SellHistoryCard />
        <SellHistoryCard />
        <SellHistoryCard />
        <SellHistoryCard />
      </Arrange>
    </Arrange>
  );
}
