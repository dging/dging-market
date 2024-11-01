import React from 'react';
import { Outlet } from 'react-router-dom';
import { useTheme } from 'styled-components';
import { Arrange } from '../../components/Base';
import { TradeDetailMenu } from '../../components/Menu';

export default function DefaultLayout() {
  const theme = useTheme();
  return (
    <Arrange minwidth={theme.page_size.minwidth}>
      <TradeDetailMenu />
      <Outlet />
    </Arrange>
  );
}
