import React from 'react';
import { useTheme } from 'styled-components';
import { Arrange } from '../../components/Base';
import { Outlet } from 'react-router-dom';
import { MystoreMenu } from '../../components/Menu';

export default function MystoreLayout() {
  const theme = useTheme();
  return (
    <Arrange minwidth={theme.page_size.minwidth}>
      <MystoreMenu />
      <Outlet />
    </Arrange>
  );
}
