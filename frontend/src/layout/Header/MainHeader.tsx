import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled, { useTheme } from 'styled-components';
import ImgBtn from '../../components/Button/ImgBtn';
import Searchbar from '../../components/Input/Searchbar';
import Arrange from '../../components/Base/Arrange';
import Logo from '../../assets/images/Logo.png';
import MainHeaderMenu from '../../components/Menu/MainHeaderMenu';

export default function MainHeader() {
  const navigate = useNavigate();
  const theme = useTheme();

  return (
    <Arrange
      width='100%'
      padding={`${theme.size.xxxl} 0`}
      display='flex'
      justifycontent='center'
      alignitems='center'
      $bottom={true}
    >
      <Arrange
        width={theme.page_size.width_s}
        display='flex'
        justifycontent='space-between'
        alignitems='center'
      >
        <ImgBtn width='110px' height='27px' $backgroundimage={Logo} />
        <Searchbar />
        <MainHeaderMenu />
      </Arrange>
    </Arrange>
  );
}
