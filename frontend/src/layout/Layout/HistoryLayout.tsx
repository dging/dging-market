import React from 'react';
import { Outlet } from 'react-router-dom';
import { Arrange, WrapLayout } from '../../components/Base';
import { TradeDetailMenu } from '../../components/Menu';
import { useTheme } from 'styled-components';

export default function DefaultLayout() {
  const theme = useTheme();
  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth}>
      <TradeDetailMenu />
      <Outlet />
    </Arrange>
  );
}
