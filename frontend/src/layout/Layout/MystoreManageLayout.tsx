import React from 'react';
import { Arrange, WrapLayout } from '../../components/Base';
import { Outlet } from 'react-router-dom';
import { MystoreMenu } from '../../section/Menu';
import { useTheme } from 'styled-components';

export default function MystoreManageLayout() {
  const theme = useTheme();
  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth}>
      <MystoreMenu />
      <Outlet />
    </Arrange>
  );
}
