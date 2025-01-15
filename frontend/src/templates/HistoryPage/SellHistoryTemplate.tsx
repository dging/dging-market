import React from 'react';
import { useTheme } from 'styled-components';
import { useReviewModal } from '../../recoil/reviewModal/useReviewModal';
import { ReviewModal } from '../../layout/Modal';
import {
  Arrange,
  ImgBtn,
  Searchbar,
  SellHistoryCard,
  RoundCategory,
  TradeCategory,
} from '../../components';
import { sellData, words } from '../../utils/_data';
import { Filter } from '../../assets/images';

export default function SellHistoryTemplate() {
  const theme = useTheme();
  const { showModal } = useReviewModal();

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
        {sellData.map((val) => (
          <SellHistoryCard content={val} key={val.id} />
        ))}
      </Arrange>
    </Arrange>
  );
}