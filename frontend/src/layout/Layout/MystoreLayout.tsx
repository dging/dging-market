import React from 'react';
import { Arrange, WrapLayout } from '../../components/Base';
import { Outlet } from 'react-router-dom';
import StoreProfile from '../../section/Profile/StoreProfile';
import { MystoreTitle } from '../../components/Title';
import { MystoreMainMenu } from '../../section/Menu';
import { useTheme } from 'styled-components';

export default function MystoreLayout() {
  const theme = useTheme();
  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth}>
      <StoreProfile />
      <MystoreMainMenu />
      <MystoreTitle />

      <Outlet />
    </Arrange>
  );
}
