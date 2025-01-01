import React from 'react';
import { Outlet } from 'react-router-dom';
import { useTheme } from 'styled-components';
import { Arrange } from '../../components';
import { MystoreMenu } from '../../section';

export default function MystoreManageLayout() {
  const theme = useTheme();
  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth}>
      <MystoreMenu />
      <Outlet />
    </Arrange>
  );
}
