import React from 'react';
import RoundCategory from '../components/Category/RoundCategory';
import TradeCategory from '../components/Category/TradeCategory';

export default function MainPage() {
  const words = ['전체', '판매중', '예약중', '판매완료'];
  return (
    <div>
      MainPage
      <RoundCategory words={words} />
      <TradeCategory />
    </div>
  );
}
