import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../../components/Base';
import RoundButton from '../../components/Button/RoundBtn';
import { MainCard } from '../../components/Card';
import StoreProfile from '../../section/Profile/StoreProfile';
import { MystoreMainMenu } from '../../section/Menu';

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

export default function MystoreGoodsTemplate() {
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
        width='100%'
        display='flex'
        justifycontent='space-between'
        alignitems='center'
        padding='20px 0 50px 0'
      >
        <WrapBlack display='flex' gap='10px'>
          <Arrange>|</Arrange>
          <Arrange>전체</Arrange>
          <WrapGrey>9</WrapGrey>
        </WrapBlack>
        <Arrange display='flex' gap='10px'>
          {type.map((val, idx) => (
            <RoundButton
              key={idx}
              $status={status === type[idx]}
              onClick={() => setStatus(type[idx])}
            >
              {val}
            </RoundButton>
          ))}
        </Arrange>
      </Arrange>

      <WrapCard
        width='100%'
        display='grid'
        gap='50px'
        justifycontent='space-between'
      >
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
        <MainCard />
      </WrapCard>
    </Arrange>
  );
}
