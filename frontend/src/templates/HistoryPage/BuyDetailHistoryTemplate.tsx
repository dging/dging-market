import React from 'react';
import styled, { useTheme } from 'styled-components';
import { Arrange } from '../../components';

export default function BuyDetailHistoryTemplate() {
  const theme = useTheme();

  return (
    <Arrange width={theme.page_size.width} padding='20px 0' margin='0 auto'>
      BuyDetailHistoryTemplate
    </Arrange>
  );
}
