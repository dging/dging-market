import React from 'react';
import { Outlet } from 'react-router-dom';
import { useTheme } from 'styled-components';
import { Arrange, MystoreTitle } from '../../components';
import { MystoreMainMenu, StoreProfile } from '../../section';

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
