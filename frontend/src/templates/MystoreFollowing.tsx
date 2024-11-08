import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../components/Base';
import { FollowingCard } from '../components/Card';

export default function MystoreFollowing() {
  const type = ['최신순', '인기순', '저가순', '고가순'];
  const [status, setStatus] = useState(type[0]);
  const theme = useTheme();
  return (
    <Arrange
      width={theme.page_size.width}
      margin='0 auto'
      padding='0 0 100px 0'
    >
      <Arrange
        display='flex'
        width='100%'
        padding='20px 0 0 0'
        flexdirection='column'
        gap='50px'
      >
        <FollowingCard />
        <FollowingCard />
        <FollowingCard />
        <FollowingCard />
        <FollowingCard />
        <FollowingCard />
        <FollowingCard />
      </Arrange>
    </Arrange>
  );
}
