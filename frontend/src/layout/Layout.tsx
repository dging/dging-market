import React from 'react';
import { Outlet } from 'react-router-dom';
import LoginHeader from './Header/LoginHeader';
import MainHeader from './Header/MainHeader';
import Footer from './Footer/Footer';

export const Layout = () => {
  return (
    <div style={{ minWidth: '1200px' }}>
      <LoginHeader />
      <MainHeader />
      <Outlet />
      <Footer />
    </div>
  );
};
