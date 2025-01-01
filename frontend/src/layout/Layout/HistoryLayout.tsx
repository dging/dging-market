import React from 'react';
import { Outlet } from 'react-router-dom';
import { Arrange } from '../../components';
import { TradeDetailMenu } from '../../section';
import { useTheme } from 'styled-components';

export default function HistoryLayout() {
  const theme = useTheme();
  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth} margin='0 auto'>
      <TradeDetailMenu />
      <Outlet />
    </Arrange>
  );
}
