import React from 'react';
import styled, { useTheme } from 'styled-components';
import { useReviewModal } from '../../recoil/reviewModal/useReviewModal';
import { ReviewModal } from '../../layout/Modal';
import {
  Arrange,
  BuyHistoryCard,
  ImgBtn,
  Searchbar,
  RoundCategory,
  TradeCategory,
} from '../../components';
import { BuyData, words } from '../../utils/_data';
import { Filter } from '../../assets/images';

export default function BuyHistoryTemplate() {
  const theme = useTheme();
  const { showModal } = useReviewModal();

  return (
    <>
      {showModal && <ReviewModal />}
      <Arrange width={theme.page_size.width} margin='0 auto'>
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
          {BuyData.map((val) => (
            <BuyHistoryCard content={val} key={val.id} />
          ))}
        </Arrange>
      </Arrange>
    </>
  );
}
