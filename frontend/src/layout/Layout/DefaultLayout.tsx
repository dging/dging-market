import React from 'react';
import { Outlet } from 'react-router-dom';
import { useTheme } from 'styled-components';
import { Arrange, WrapLayout } from '../../components/Base';
import { LoginHeader, MainHeader } from '../Header';
import Footer from '../Footer/Footer';

export default function DefaultLayout() {
  const theme = useTheme();
  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth}>
      <LoginHeader />
      <MainHeader />
      <Outlet />
      <Footer />
    </Arrange>
  );
}
