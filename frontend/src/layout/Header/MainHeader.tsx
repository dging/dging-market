import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useTheme } from 'styled-components';
import { MainHeaderMenu } from '../../section';
import { Arrange, ImgBtn, Searchbar } from '../../components';
import { Logo } from '../../assets/images';

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
    >
      <Arrange
        width={theme.page_size.width}
        display='flex'
        justifycontent='space-between'
        alignitems='center'
      >
        <ImgBtn
          width='110px'
          height='27px'
          $backgroundimage={Logo}
          onClick={() => navigate('/')}
        />
        <Searchbar $type={true} />
        <MainHeaderMenu />
      </Arrange>
    </Arrange>
  );
}
