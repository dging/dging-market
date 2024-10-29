import React, { useState } from 'react';
import styled, { useTheme } from 'styled-components';
import ImgBtn from '../../components/Button/ImgBtn';
import Searchbar from '../../components/Input/Searchbar';
import Arrange from '../../components/Base/Arrange';
import Logo from '../../assets/images/Logo.png';

const WrapMainHeader = styled(Arrange)`
  border-bottom: 1px solid ${({ theme }) => theme.color.black5};
`;

export default function MainHeader() {
  const theme = useTheme();

  return (
    <WrapMainHeader
      width='100%'
      padding={theme.size.xxxl}
      display='flex'
      justifycontent='center'
      alignitems='center'
    >
      <Arrange
        width={theme.page_size.width_s}
        display='flex'
        justifycontent='space-between'
        alignitems='center'
      >
        <ImgBtn width='110px' height='27px' $backgroundimage={Logo} />
        <Searchbar />
      </Arrange>
    </WrapMainHeader>
  );
}
