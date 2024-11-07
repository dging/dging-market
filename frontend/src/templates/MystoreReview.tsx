import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import RoundButton from '../components/Button//RoundBtn';
import { StoreReviewScoreCard, StoreReviewCard } from '../components/Card';
import StoreProfile from '../components/Profile/StoreProfile';
import { MystoreMainMenu } from '../components/Menu';

const WrapBlack = styled(Arrange)`
  ${({ theme }) => theme.font.r18}
`;

const WrapGrey = styled(Arrange)`
  color: ${({ theme }) => theme.color.black2};
`;

const WrapCard = styled(Arrange)`
  width: 100%;
  grid-template-columns: repeat(4, 1fr);
`;

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
      <StoreReviewCard />
    </Arrange>
  );
}
