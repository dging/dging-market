import React from 'react';
import { Arrange, WrapLayout } from '../../components/Base';
import { Outlet } from 'react-router-dom';
import { MystoreMenu } from '../../components/Menu';
import { useTheme } from 'styled-components';

export default function MystoreLayout() {
  const theme = useTheme();
  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth}>
      <MystoreMenu />
      <Outlet />
    </Arrange>
  );
}
