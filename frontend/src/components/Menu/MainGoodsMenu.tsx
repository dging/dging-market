import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../Base';
import { ImgBtn } from '../Button';
import CD from '../../assets/images/CD.png';
import Cassette from '../../assets/images/Cassette.png';
import DVD from '../../assets/images/DVD.png';
import Vinyl from '../../assets/images/Vinyl.png';

const WrapMainGoodsMenu = styled(Arrange)`
  background-color: ${({ theme }) => theme.color.pink1};
`;

const Text = styled.div`
  ${({ theme }) => theme.font.b32}
  text-align: center;
`;

const WrapMenuItem = styled(Arrange)``;

export default function MainGoodsMenu() {
  const theme = useTheme();
  const items = ['CD', 'Vinyl', 'Cassette', 'DVD'];
  return (
    <WrapMainGoodsMenu
      display='flex'
      flexdirection='column'
      width='100%'
      padding={theme.size.xxxxl}
      gap={theme.size.xxxxxxl}
    >
      <Text>어떤 상품을 찾으시나요?</Text>
      <Arrange display='flex' gap='67px'>
        <ImgBtn as='div' width='240px' height='240px' $backgroundimage={CD} />
      </Arrange>
    </WrapMainGoodsMenu>
  );
}
