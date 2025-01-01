import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange, FollowerCard } from '../../components';

const WrapCard = styled(Arrange)`
  grid-template-columns: repeat(4, 1fr);
`;

export default function MystoreFollowerTemplate() {
  const type = ['최신순', '인기순', '저가순', '고가순'];
  const [status, setStatus] = useState(type[0]);
  const theme = useTheme();
  return (
    <WrapCard
      display='grid'
      width={theme.page_size.width}
      margin='0 auto'
      padding='20px 0 100px 0'
      gap='20px'
    >
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
      <FollowerCard />
    </WrapCard>
  );
}
