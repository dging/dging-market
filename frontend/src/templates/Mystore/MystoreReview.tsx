import React, { useState } from 'react';
import { useTheme } from 'styled-components';
import {
  Arrange,
  StoreReviewScoreCard,
  StoreReviewCard,
} from '../../components';

export default function MystoreReview() {
  const type = ['최신순', '인기순', '저가순', '고가순'];
  const [status, setStatus] = useState(type[0]);
  const theme = useTheme();
  return (
    <Arrange
      width={theme.page_size.width}
      margin='0 auto'
      padding='0 0 100px 0'
    >
      <StoreReviewScoreCard />
      <Arrange
        display='flex'
        flexdirection='column'
        width='100%'
        padding={`${theme.size.xxxxl} 0 ${theme.size.xl} 0`}
        gap='40px'
      >
        <StoreReviewCard />
        <StoreReviewCard />
        <StoreReviewCard />
        <StoreReviewCard />
        <StoreReviewCard />
        <StoreReviewCard />
        <StoreReviewCard />
        <StoreReviewCard />
      </Arrange>
    </Arrange>
  );
}
