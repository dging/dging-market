import React from 'react';
import { Outlet } from 'react-router-dom';
import { useTheme } from 'styled-components';
import { Arrange } from '../../components';
import { LoginHeader, MainHeader } from '../Header';
import ResetRecoilComponent from '../../utils/ResetRecoilComponent';
import Footer from '../Footer/Footer';

export default function DefaultLayout() {
  const theme = useTheme();
  return (
    <>
      <ResetRecoilComponent />
      <Arrange width='100%' minwidth={theme.page_size.minwidth}>
        <LoginHeader />
        <MainHeader />
        <Outlet />
        <Footer />
      </Arrange>
    </>
  );
}
