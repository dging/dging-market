import React, { useState, useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import { useTheme } from 'styled-components';
import { Arrange, MystoreTitle } from '../../components';
import { MystoreMainMenu, StoreProfile } from '../../section';
import { MyStoreType } from '../../types/storeType';

export default function MystoreLayout() {
  const theme = useTheme();
  const [data, setData] = useState<MyStoreType | null>(null);

  return (
    <Arrange width='100%' minwidth={theme.page_size.minwidth}>
      <StoreProfile />
      <MystoreMainMenu />
      <MystoreTitle />
      {/* router마다 안에 보여지는게 달라짐 */}
      <Outlet />
    </Arrange>
  );
}
